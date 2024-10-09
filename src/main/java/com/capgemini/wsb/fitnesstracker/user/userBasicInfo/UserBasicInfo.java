package com.capgemini.wsb.fitnesstracker.user.userBasicInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserBasicInfo {
    private Long id;

    private String firstName;

    private String lastName;
}
