package ch.zhaw.sml.iwi.meng.leantodo.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo,Long> {
    public List<ToDo> findByOwner(String owner);
   
    @Query("SELECT t FROM ToDo as t WHERE t.owner = ?1 AND t.archived = false")
    public List<ToDo> findAllButArchivedByOwner(String owner);

    @Query("SELECT t FROM ToDo as t WHERE t.owner = ?1 AND t.title = ?2 AND t.status = ?3 AND t.category = ?4")
    public List<ToDo> findFilterToDo(String owner, String title, StatusEnum status, CategoryEnum category);
    
}