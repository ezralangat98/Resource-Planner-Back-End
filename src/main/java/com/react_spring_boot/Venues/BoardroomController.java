package com.react_spring_boot.Venues;

import com.react_spring_boot.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/")

public class BoardroomController {

    @Autowired
    private BoardroomRepository boardroomRepository;

    /** READ or LIST*/
    @GetMapping("/venue")
    public List<Boardroom> getAllRooms(){
        return  boardroomRepository.findAll();
    }

    /** Find By ID */
    @GetMapping("/venue/{boardroom_id}")
    public ResponseEntity<Boardroom> getRoomById(@PathVariable(value = "boardroom_id") Integer boardroom_id) throws ResourceNotFoundException {
        Boardroom boardroom = boardroomRepository.findById(boardroom_id)
                .orElseThrow(() -> new ResourceNotFoundException("Boardroom not found for this id :: " + boardroom_id));

        return  ResponseEntity.ok(boardroom);

    }


    /** CREATE / SAVE  */
    @PostMapping("/venue/create")
    public Boardroom saveRoom(@RequestBody Boardroom boardroom){
        return boardroomRepository.save(boardroom);
    }




    /** UPDATE */
    @PutMapping("/venue/update/{boardroom_id}")
    public ResponseEntity<Boardroom> updateRoomById(@PathVariable(value = "boardroom_id") Integer boardroom_id,
                                                    @RequestBody Boardroom boardroomDetails) throws ResourceNotFoundException {

        Boardroom boardroom = boardroomRepository.findById(boardroom_id)
                .orElseThrow(() -> new ResourceNotFoundException("Boardroom not found for this id :: " + boardroom_id));

        boardroom.setBoardroomName(boardroomDetails.getBoardroomName());
        boardroom.setCapacity(boardroomDetails.getCapacity());
        boardroom.setTv(boardroomDetails.getTv());
        boardroom.setWhiteboard(boardroomDetails.getWhiteboard());
        boardroom.setConferencePhone(boardroomDetails.getConferencePhone());

        Boardroom updatedBoardroom = boardroomRepository.save(boardroom);

        return  ResponseEntity.ok(updatedBoardroom);

    }


    /** DELETE */
    @DeleteMapping("/venue/delete/{boardroom_id}")
    public Map<String, Boolean> deleteRoom(@PathVariable(value = "boardroom_id") Integer boardroom_id) throws ResourceNotFoundException{

        Boardroom boardroom = boardroomRepository.findById(boardroom_id)
                .orElseThrow(() -> new ResourceNotFoundException("Boardroom not found for this id :: " + boardroom_id));

        boardroomRepository.delete(boardroom);

        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);

        return response;

    }

}
