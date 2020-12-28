package com.example.webback.web.api.v1;

import com.example.webback.business.entity.UserEntity;
import com.example.webback.business.service.UserService;
import com.example.webback.web.api.dto.read.DietsReadDto;
import com.example.webback.web.api.dto.read.PointReadDto;
import com.example.webback.web.api.dto.read.WorkoutReadDto;
import com.example.webback.web.api.dto.update.ProgressDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.example.webback.utils.ApiConstantUtils.USER;

@RestController
@RequestMapping(USER)
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public UserEntity findById(@PathVariable UUID id) {
        return userService.getById(id);
    }

    @GetMapping("/diets/{id}")
    public DietsReadDto getUserDiet(@PathVariable UUID id) {
        return userService.getDiet(id);
    }

    @GetMapping("/workout/{id}")
    public WorkoutReadDto getUserWorkout(@PathVariable UUID id) {
        return userService.getWorkout(id);
    }

    @PutMapping
    public void updateUserProgress(@RequestBody ProgressDto dto) {
        userService.updateProgress(dto);
    }

    @GetMapping("/points/{id}")
    public List<PointReadDto> getUserProgress(@PathVariable UUID id) {
        return userService.getPoints(id);
    }


}
