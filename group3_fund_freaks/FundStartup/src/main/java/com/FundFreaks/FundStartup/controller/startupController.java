package com.FundFreaks.FundStartup.controller;

import com.FundFreaks.FundStartup.exception.ideaAlreadyExist;
import com.FundFreaks.FundStartup.exception.startupAlreadyExist;
import com.FundFreaks.FundStartup.exception.startupNotFound;
import com.FundFreaks.FundStartup.model.ideaModel;
import com.FundFreaks.FundStartup.model.startupModel;
import com.FundFreaks.FundStartup.repository.startupRepo;
import com.FundFreaks.FundStartup.service.startupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/startup")
public class startupController {
    @Autowired
    private startupService service;
    @GetMapping("/greet")
    public ResponseEntity<?> greetStartup()
    {
        return new ResponseEntity<>("Hi Geeks , ready to create something new?",HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<?> addStartup(@RequestBody startupModel m) throws startupAlreadyExist
    {
        try {
            startupModel u11=service.saveStartup(m);
            return new ResponseEntity<>(u11,HttpStatus.CREATED);
        }
        catch(startupAlreadyExist st)
        {
            throw new startupAlreadyExist();
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/addInfo/{id}")
    public ResponseEntity<?> updateInfo(@PathVariable int id,@RequestBody startupModel model)
    {
        return new ResponseEntity<>(service.updateInfoo(id,model),HttpStatus.OK);
    }
    @GetMapping("/getSById/{id}")
    public ResponseEntity<?> listStartups(@PathVariable int id) throws startupNotFound {
        try{
            startupModel model=service.getById(id);
            return new ResponseEntity<>(model, HttpStatus.OK);
        }
        catch(startupNotFound st)
        {
            throw new startupNotFound();
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/getByCategory/{category}")
    public ResponseEntity<?> getIdeasByCategories(@PathVariable String category) {
        return new ResponseEntity<>(service.findIdeaByCategory(category), HttpStatus.OK);
    }
    @GetMapping("/getByCity/{city}")
    public ResponseEntity<?> findByCity(@PathVariable String city)
    {
        return new ResponseEntity<>(service.findCity(city),HttpStatus.OK);
    }

    @PutMapping("/addIdea/{id}")
    public ResponseEntity<?> addIdeas(@PathVariable int id, @RequestBody ideaModel idd) throws ideaAlreadyExist {
        try{
            return new ResponseEntity<>(service.addIdea(idd, id), HttpStatus.CREATED);
        }
        catch(ideaAlreadyExist s)
        {
            throw new ideaAlreadyExist();
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }

    }
    @PutMapping("/updateIdea/{startupId}/{ideaId}")
    public ResponseEntity<?> updateIdeas(@PathVariable int startupId,@PathVariable int ideaId, @RequestBody ideaModel idd) throws startupNotFound {
        try{
            return new ResponseEntity<>(service.updateIdea(startupId, ideaId,idd), HttpStatus.CREATED);
        }
        catch (startupNotFound str)
        {
            throw new startupNotFound();
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }

    }
//    @GetMapping("/sendInfo")
//    public ResponseEntity<?> sendInfo()
//    {
//        return new ResponseEntity<>(service.sendData(),HttpStatus.OK);
//    }
    @DeleteMapping("/deleteIdea/{id}/{idd}")
    public ResponseEntity<?> deleteIdea(@PathVariable int id,@PathVariable int idd) throws startupNotFound {
        try
        {
            return new ResponseEntity<>( service.deleteIdea(id,idd),HttpStatus.OK);
        }
        catch(startupNotFound st)
        {
            throw new startupNotFound();
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/accept/{sid}/{ideaId}/{investorId}")
    public ResponseEntity<?> fetchIdea(@PathVariable int sid,@PathVariable int ideaId,@PathVariable int investorId)
    {
        return new ResponseEntity<>(service.fetchIdeaSe(sid,ideaId,investorId),HttpStatus.OK);
    }
    @GetMapping("/getIByS/{id}")
    public ResponseEntity<?> getIdea(@PathVariable int id)
    {
        return new ResponseEntity<>(service.getIdee(id),HttpStatus.OK);
    }
    @GetMapping("/getSByName/{name}")
    public ResponseEntity<?> getName(@PathVariable String name)
    {
        return new ResponseEntity<>(service.getName(name),HttpStatus.OK);
    }
    @GetMapping("/showAll")
    public ResponseEntity<?> showALl()
    {
        return new ResponseEntity<>(service.getAll(),HttpStatus.OK);
    }
    @GetMapping("/showInterest/{startupId}/{ideaId}")
    public ResponseEntity<?> showInterest(@PathVariable int startupId,@PathVariable int ideaId){return new ResponseEntity<>(service.showI(startupId,ideaId),HttpStatus.OK);}
}
