package com.walterbernal.projectwithsql.models;

import jakarta.persistence.*;

// Auto-generates methods (getters-setters)
import lombok.Data;
import lombok.Setter;
import lombok.Getter;

@Data
@Entity
@Getter
@Setter
@Table(name="employees")
public class User implements Account {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name="first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;

  @Column(name = "is_admin")
  private boolean isAdmin = false;

  public User() {
    super();
  }

  public User(long id, String firstName, String lastName, String email, boolean isAdmin) {
    super();
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.isAdmin = isAdmin;
  }

  @Override
  public String toString() {
    return "employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
  }


  @Override
  public boolean getIsAdmin() {
    return this.isAdmin;
  }

  @Override
  public boolean authenticate(String email, String password) {
    return this.password.equals(password) && this.email.equals(email);
  }
}
