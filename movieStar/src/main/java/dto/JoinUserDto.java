package dto;

import com.example.movieStar.domain.UserRole;
import com.example.movieStar.domain.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JoinUserDto {

    private String password;
    private String name;
    private String email;
    private UserRole role;

    /* DTO -> Entity */
    public UserEntity toEntity() {
        UserEntity userEntity = UserEntity.builder()
                .password(password)
                .name(name)
                .email(email)
                .role(UserRole.ROLE_USER)
                .build();
        return userEntity;
    }


}