package io.jeidiiy.sirenordersystem.user.domain;

import jakarta.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@ToString
@NoArgsConstructor
@Table(
    name = "\"users\"",
    indexes = {@Index(columnList = "username")})
@Entity
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer userId;

  @Setter
  @Column(nullable = false)
  private String username;

  @Setter
  @Column(length = 100, nullable = false)
  private String password;

  @Setter
  @Column(length = 6, nullable = false)
  private String realName;

  @Setter
  @Column(length = 6)
  private String nickname;

  @Enumerated(EnumType.STRING)
  @Column
  private Role role;

  @Builder
  public User(String username, String realName, String password, String nickname) {
    this.username = username;
    this.realName = realName;
    this.password = password;
    this.nickname = nickname;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (this.role == Role.ADMIN) {
      return List.of(
          new SimpleGrantedAuthority(Role.ADMIN.name()),
          new SimpleGrantedAuthority(Role.CUSTOMER.name()),
          new SimpleGrantedAuthority("ROLE_" + Role.ADMIN.name()),
          new SimpleGrantedAuthority("ROLE_" + Role.CUSTOMER.name()));
    }

    return List.of(
        new SimpleGrantedAuthority(Role.CUSTOMER.name()),
        new SimpleGrantedAuthority("ROLE_" + Role.CUSTOMER.name()));
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;

    if (user.getUserId() != null) {
      return Objects.equals(getUserId(), user.getUserId());
    }

    return Objects.equals(getUsername(), user.getUsername())
        && Objects.equals(getPassword(), user.getPassword())
        && Objects.equals(getRealName(), user.getRealName())
        && Objects.equals(getNickname(), user.getNickname())
        && getRole() == user.getRole();
  }

  @Override
  public int hashCode() {
    if (getUserId() != null) {
      return Objects.hash(getUserId());
    }
    return Objects.hash(getUsername(), getPassword(), getRealName(), getNickname(), getRole());
  }
}
