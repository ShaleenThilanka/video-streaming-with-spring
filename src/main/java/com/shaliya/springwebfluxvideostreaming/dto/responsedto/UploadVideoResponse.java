package com.shaliya.springwebfluxvideostreaming.dto.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadVideoResponse {
    private Long videoId;
    private String videoUrl;
}
