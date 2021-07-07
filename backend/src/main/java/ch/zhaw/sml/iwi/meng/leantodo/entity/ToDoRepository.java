package ch.zhaw.sml.iwi.meng.leantodo.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo,Long> {
    public List<ToDo> findByOwner(String owner);
   
    @Query("SELECT t FROM ToDo as t WHERE t.owner = ?1 AND t.status != 'DONE'")
    public List<ToDo> findAllButArchivedByOwner(String owner);

    @Query("SELECT t FROM ToDo as t WHERE t.owner = ?1 AND t.status != 'DONE' AND t.due <= ?2")
    public List<ToDo> findAllDueToday(String owner,Date date);

    @Query("SELECT t FROM ToDo as t WHERE t.owner = ?1 AND t.title LIKE %?2%")
    public List<ToDo> filterByTitle(String owner,String title);

    @Query("SELECT t FROM ToDo as t WHERE t.owner = ?1 AND t.status = ?2")
    public List<ToDo> filterByStatus(String owner,StatusEnum status);

    @Query("SELECT t FROM ToDo as t WHERE t.owner = ?1 AND t.category = ?2")
    public List<ToDo> filterByCategory(String owner,CategoryEnum category);

    @Query("SELECT t FROM ToDo as t WHERE t.owner = ?1 AND t.title LIKE %?2% AND t.status = ?3")
    public List<ToDo> filterByTitleAndStatus(String owner,String title,StatusEnum status);

    @Query("SELECT t FROM ToDo as t WHERE t.owner = ?1 AND t.title LIKE %?2% AND t.category = ?3")
    public List<ToDo> filterByTitleAndCategory(String owner,String title,CategoryEnum category);
    
    @Query("SELECT t FROM ToDo as t WHERE t.owner = ?1 AND t.status = ?2 AND t.category = ?3")
    public List<ToDo> filterByStatusAndCategory(String owner,StatusEnum status,CategoryEnum category);

    @Query("SELECT t FROM ToDo as t WHERE t.owner = ?1 AND t.title LIKE %?2% AND t.status = ?3 AND t.category = ?4")
    public List<ToDo> filterByTitleAndStatusAndCategory(String owner,String title,StatusEnum status,CategoryEnum category);

}