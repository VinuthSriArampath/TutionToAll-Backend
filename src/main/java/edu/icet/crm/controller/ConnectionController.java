package edu.icet.crm.controller;

import edu.icet.crm.util.ResponseMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/connections")
public class ConnectionController {
    @GetMapping("/test")
    public ResponseEntity<ResponseMessage> testConnections(){
        return ResponseEntity.ok().body(new ResponseMessage("Connections Established!"));
    }
}
