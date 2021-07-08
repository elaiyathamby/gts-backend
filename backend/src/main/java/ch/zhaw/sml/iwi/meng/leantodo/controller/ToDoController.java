package ch.zhaw.sml.iwi.meng.leantodo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.zhaw.sml.iwi.meng.leantodo.entity.CategoryEnum;
import ch.zhaw.sml.iwi.meng.leantodo.entity.StatusEnum;
import ch.zhaw.sml.iwi.meng.leantodo.entity.ToDo;
import ch.zhaw.sml.iwi.meng.leantodo.entity.ToDoRepository;

@Component
public class ToDoController {

    @Autowired
    private ToDoRepository toDoRepository;

    public List<ToDo> listAllToDos(String loginName) {
        return toDoRepository.findAllButArchivedByOwner(loginName);
    }

    public List<ToDo> listAllToDosDueToday(String loginName) {
        return toDoRepository.findAllDueToday(loginName, new Date());
    }

    public ToDo getToDoById(String loginName, Long id) {
        return toDoRepository.findByIdAndOwner(loginName, id);
    }

    public List<ToDo> listFilterToDos(String loginName, String title, StatusEnum status, CategoryEnum category) {
        if (title != null && status != null && category != null) {
            return toDoRepository.filterByTitleAndStatusAndCategory(loginName, title, status, category);
        } else if (title != null) {
            if (status != null) {
                return toDoRepository.filterByTitleAndStatus(loginName, title, status);
            } else if (category != null) {
                return toDoRepository.filterByTitleAndCategory(loginName, title, category);
            } else {
                return toDoRepository.filterByTitle(loginName, title);
            }
        } else if (status != null) {
            if (category != null) {
                return toDoRepository.filterByStatusAndCategory(loginName, status, category);
            } else {
                return toDoRepository.filterByStatus(loginName, status);
            }
        } else if (category != null) {
            return toDoRepository.filterByCategory(loginName, category);
        }

        return toDoRepository.findAllButArchivedByOwner(loginName);
    }

    public void persistToDo(ToDo newToDo, String owner) {
        newToDo.setOwner(owner);
        newToDo.setId(null);
        newToDo.setOpen(new Date());
        newToDo.setStatus(StatusEnum.OPEN);
        toDoRepository.save(newToDo);
    }

    public void updateToDo(ToDo toDo, String owner) {
        ToDo orig = toDoRepository.getOne(toDo.getId());
        // Check if the original ToDo was present and that it belonged to the same owner
        if (orig == null || !orig.getOwner().equals(owner)) {
            return;
        }
        // Ok, let's overwrite the existing toDo.
        toDo.setOwner(owner); // Set the owner because this property is ignored in the Rest API
        toDoRepository.save(toDo);
    }

}