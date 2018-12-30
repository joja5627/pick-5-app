package io.pick5.cloud.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HostInfo {

    private String address;
    private int port;
    private String hostName;

}
