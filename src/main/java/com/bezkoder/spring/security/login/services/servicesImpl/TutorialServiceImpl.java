package com.bezkoder.spring.security.login.services.servicesImpl;

import com.bezkoder.spring.security.login.models.Tutorial;
import com.bezkoder.spring.security.login.repository.TutorialRepository;
import com.bezkoder.spring.security.login.services.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service

// Class
class TutorialServiceImpl implements TutorialService {

    @Autowired
    private TutorialRepository tutorialRepository;

    // Save operation
    @Override
    public Tutorial saveTutorial(Tutorial tutorial)
    {
        return tutorialRepository.save(tutorial);
    }

    // Read operation
    @Override public List<Tutorial> fetchTutorialList()
    {
        return (List<Tutorial>)
                tutorialRepository.findAll();
    }

    // Update operation
    @Override
    public Tutorial updateTutorial(Tutorial tutorial, Long TutorialId)
    {
        Tutorial depDB
                = tutorialRepository.findById(TutorialId)
                .get();

        if (Objects.nonNull(tutorial.getTitle())
                && !"".equalsIgnoreCase(
                tutorial.getTitle())) {
            depDB.setTitle(
                    tutorial.getTitle());
        }


        return tutorialRepository.save(depDB);
    }

    // Delete operation
    @Override
    public void deleteTutorialById(Long TutorialId)
    {
        tutorialRepository.deleteById(TutorialId);
    }
}
