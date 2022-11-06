package xenius.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xenius.model.Band;
import xenius.service.BandServiceInterface;

import java.util.Set;

@RestController
public class BandController {

    private BandServiceInterface bandServiceInterface;

    public BandController(BandServiceInterface bandServiceInterface) {
        this.bandServiceInterface = bandServiceInterface;
    }

    @PostMapping("/createBand")
    public ResponseEntity<String> createUser(@RequestBody Band band){
        String msg="";
        if(bandServiceInterface.save(band)!=null) {
            msg="Oprettet band: "+band.getName();
        }else {
            msg="fejl i oprettelse af " + band.getName();
        }
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @GetMapping("/getAllBands")
    public ResponseEntity<Set<Band>> getAllBands(){
        return new ResponseEntity<>(bandServiceInterface.findAll(), HttpStatus.OK);
    }
}









