package io.jeidiiy.sirenordersystem.order.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
@Table(name = "beverage_options")
@Entity
public class BeverageOption {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer orderMenuOptionId;

  @Column(length = 30, nullable = false)
  private String name;

  private Type type;

  @Column(length = 30)
  private String defaultValue;

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    BeverageOption that = (BeverageOption) o;

    if (that.getOrderMenuOptionId() != null) {
      return Objects.equals(getOrderMenuOptionId(), that.getOrderMenuOptionId());
    }
    return Objects.equals(getName(), that.getName())
        && getType() == that.getType()
        && Objects.equals(getDefaultValue(), that.getDefaultValue());
  }

  @Override
  public int hashCode() {
    if (getOrderMenuOptionId() != null) {
      return Objects.hash(getOrderMenuOptionId());
    }

    return Objects.hash(getName(), getType(), getDefaultValue());
  }
}
