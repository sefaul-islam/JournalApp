package net.engineeringdigest.journalApp.service;

import ch.qos.logback.core.encoder.EchoEncoder;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
  @Autowired
    private JournalEntryRepo journalEntryRepository;
  @Autowired
    private UserService userService;
  @Transactional
  public void saveEntry(JournalEntry journalEntry, String userName){
      try {
          User user= userService.findByUserName(userName);
          journalEntry.setDate(LocalDateTime.now());
          JournalEntry saved = journalEntryRepository.save(journalEntry);
          user.getJournalEntries().add(saved);
          userService.saveEntry(user);
      }catch (Exception e){
          System.out.println(e);
          throw new RuntimeException("An error occured while saving the entry. ", e);
      }

   }

   public List<JournalEntry> getAll(){
       return journalEntryRepository.findAll();
   }
   public Optional<JournalEntry> findById(ObjectId id){
       return journalEntryRepository.findById(id);
   }
   public void deleteById(ObjectId id, String userName){
       User user= userService.findByUserName(userName);
       user.getJournalEntries().removeIf(x->x.getId().equals(id));
       userService.saveEntry(user);
       journalEntryRepository.deleteById(id);
   }
}
