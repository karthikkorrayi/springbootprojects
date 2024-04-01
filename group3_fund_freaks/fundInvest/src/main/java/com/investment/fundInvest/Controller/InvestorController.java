package com.investment.fundInvest.Controller;

import com.investment.fundInvest.Exception.InvestorAlreadyExist;
import com.investment.fundInvest.Exception.InvestorNotExist;
import com.investment.fundInvest.Model.investorModel;
import com.investment.fundInvest.Service.InvestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("investor")

public class InvestorController {
    @Autowired
    private InvestorService is;
    @PostMapping("add")
    public ResponseEntity<?> addInvestor(@RequestBody investorModel im) throws InvestorAlreadyExist {
        try {
            return new ResponseEntity<>(is.addInvestor(im), HttpStatus.CREATED);
        } catch (InvestorAlreadyExist e) {
            throw new InvestorAlreadyExist();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("investor/{id}")
    public ResponseEntity<?> getInvestorDetailsById(@PathVariable int id)
    {
           return new ResponseEntity<>(is.getInvestorDetailsById(id),HttpStatus.OK);
    }
    @DeleteMapping("investor/{id}")
    public ResponseEntity<?>deleteInvestorDetailById(@PathVariable int id) throws InvestorNotExist
    {
        try {
            String output = is.deleteInvestorById(id);
            return new ResponseEntity<>(output, HttpStatus.OK);
        }
        catch(InvestorNotExist ine)
        {
            throw new InvestorNotExist();
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("addInfo/{id}")
   public ResponseEntity<?> updateInvestorById(@RequestBody investorModel im, @PathVariable int id)
    {
            return new ResponseEntity<>(is.addInfo(im,id),HttpStatus.OK);
    }
  @GetMapping("investors")
    public ResponseEntity<?> getAllInvestorDetails()
  {
   return new ResponseEntity<>(is.getAllInvestorDetails(),HttpStatus.OK);
  }
//    @PutMapping("addWish/{id}")
//    public ResponseEntity<?> addWishLists(@PathVariable int id, @RequestBody Wishlist_Investor wl) {
//        return new ResponseEntity<>(is.addWishlist(id,wl), HttpStatus.CREATED);
//    }
//
//    @DeleteMapping("deleteWish/{id}/{wish_id}")
//    public ResponseEntity<?> deleteIdea(@PathVariable int id,@PathVariable int wish_id)
//    {
//        return new ResponseEntity<>( is.deleteIdea(id,wish_id),HttpStatus.OK);
//    }
    @GetMapping("/investIn/{investorId}/{startId}/{ideaId}")
    public ResponseEntity<?> investIn(@PathVariable int investorId,@PathVariable int startId,@PathVariable int ideaId)
    {
        return new ResponseEntity<>(is.showInterest(investorId,startId,ideaId),HttpStatus.OK);
    }
}
