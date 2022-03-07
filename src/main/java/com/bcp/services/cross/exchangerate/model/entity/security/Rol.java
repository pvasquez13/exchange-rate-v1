package com.bcp.services.cross.exchangerate.model.entity.security;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Table(name = "roles")
public class Rol {

  @Id
  @GeneratedValue
  private Long id;

  @Column(length = 60)
  private String name;

}
