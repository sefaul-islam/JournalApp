package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepo;
import net.engineeringdigest.journalApp.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
  @Autowired
    private UserRepo userRepository;

   public void saveEntry(User userEntry){
       userRepository.save(userEntry);
   }

   public List<User> getAll(){
       return userRepository.findAll();
   }
   public Optional<User> findById(ObjectId id){
       return userRepository.findById(id);
   }
   public void deleteById(ObjectId id){
       userRepository.deleteById(id);
   }
   public User findByUserName(String userName){
       return userRepository.findByUserName(userName);
   }
}
