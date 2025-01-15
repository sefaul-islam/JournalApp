package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.repository.JournalEntryRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class JournalEntryService {
  @Autowired
    private JournalEntryRepo journalEntryRepository;
   public void saveEntry(JournalEntry journalEntry){
       journalEntryRepository.save(journalEntry);
   }
}
