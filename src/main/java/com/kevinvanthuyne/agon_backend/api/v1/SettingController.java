package com.kevinvanthuyne.agon_backend.api.v1;

import com.kevinvanthuyne.agon_backend.dto.ChannelSettingDto;
import com.kevinvanthuyne.agon_backend.model.ChannelSetting;
import com.kevinvanthuyne.agon_backend.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/setting")
public class SettingController {
    private static final Set<ChannelSetting> CHANNELS = Set.of(ChannelSetting.SCORING,
                                                               ChannelSetting.HALL_OF_FAME,
                                                               ChannelSetting.WINNER_ANNOUNCEMENT);
    private static final Set<String> CHANNEL_STRINGS = CHANNELS.stream()
            .map(ChannelSetting::getString)
            .collect(Collectors.toSet());
    private final SettingService settingService;

    @Autowired
    public SettingController(SettingService settingService) {
        this.settingService = settingService;
    }

    @PostMapping(path = "/channel")
    public ResponseEntity<Void> setScoringChannel(@RequestBody ChannelSettingDto dto) {
        if (!CHANNEL_STRINGS.contains(dto.channel())) {
            return ResponseEntity.badRequest().build();
        }

        settingService.setSetting(dto.channel(), dto.channelId());
        return ResponseEntity.ok().build();
    }
}
