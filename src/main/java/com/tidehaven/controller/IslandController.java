package com.tidehaven.controller;

import com.tidehaven.model.Island;
import com.tidehaven.repository.IslandRepository;
import com.tidehaven.security.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/islands")
public class IslandController {

    private final IslandRepository islandRepo;

    public IslandController(IslandRepository islandRepo) {
        this.islandRepo = islandRepo;
    }

    @PostMapping("/save")
    public String save(@RequestBody String worldJson,
                       HttpServletRequest request) {

        String token = request.getHeader("Authorization").substring(7);
        Long userId = JwtService.extractUserId(token);

        Island island = islandRepo.findByUserId(userId)
                .orElse(new Island());

        island.setUserId(userId);
        island.setWorldJson(worldJson);

        islandRepo.save(island);

        return "SAVED";
    }

    @GetMapping("/load")
    public String load(HttpServletRequest request) {

        String token = request.getHeader("Authorization").substring(7);
        Long userId = JwtService.extractUserId(token);

        return islandRepo.findByUserId(userId)
                .map(Island::getWorldJson)
                .orElse("{}");
    }
}