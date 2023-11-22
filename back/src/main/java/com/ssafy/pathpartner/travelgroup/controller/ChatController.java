package com.ssafy.pathpartner.travelgroup.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import com.ssafy.pathpartner.travelgroup.dto.ChatDto;
import com.ssafy.pathpartner.travelgroup.service.ChatService;

@Slf4j
@RestController()
@CrossOrigin("*")
@RequestMapping("/chat")
@Api(tags = {"그룹 컨트롤러 API"})
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @ApiOperation(value = "채팅 리스트 조회", notes = "채팅 리스트를 조회합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "채팅 리스트 조회 성공"),
            @ApiResponse(code = 500, message = "서버 에러")})
    @GetMapping("/{group_id}")
    public ResponseEntity<List<ChatDto>> getChatList(@PathVariable("group_id") String group_id) {
        log.debug("getChatList call");

        try {
            return ResponseEntity.ok().body(chatService.selectChatList(group_id));
        } catch (SQLException e) {
            log.debug(e.toString());
            return ResponseEntity.internalServerError().build();
        }
    }

    @ApiOperation(value = "채팅 생성", notes = "채팅을 생성합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "채팅 생성 성공"),
            @ApiResponse(code = 500, message = "서버 에러")})
    @PostMapping
    public ResponseEntity<Boolean> registerChat(@RequestBody ChatDto chatDto) {
        log.debug("registerChat call");

        try {
            chatService.insertChat(chatDto);
            return ResponseEntity.ok().body(true);
        } catch (SQLException e) {
            log.debug(e.toString());
            return ResponseEntity.internalServerError().build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
