package dto;

import com.example.movieStar.domain.UserRole;
import com.example.movieStar.domain.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private String password;
    private String name;
    private String email;
    private UserRole role;

    public UserEntity toEntity() {
        UserEntity userEntity = UserEntity.builder()
                .password(password)
                .name(name)
                .email(email)
                .role(role)
                .build();
        return userEntity;
    }

}
