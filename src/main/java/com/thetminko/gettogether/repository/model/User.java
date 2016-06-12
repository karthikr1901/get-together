package com.thetminko.gettogether.repository.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by developer on 11/6/16.
 */

@Entity
@Table
public class User implements Serializable {
  private static final long serialVersionUID = -2253338676290772048L;
  private long id;
  private String uuid;
  private String firstName;
  private String lastName;
  private String gender;
  private LocalDate dateOfBirth;
  private String email;
  private String contactNo;
  private String address;
  private int postalCode;
  private String fbUserId;
  private UserCredential userCredential;
  private List<Permission> permissions;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @Column(nullable = false, length = 100)
  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  @Column(nullable = false, length = 100)
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Column(nullable = false, length = 100)
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Column(length = 10)
  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  @Column
  @JsonSerialize(using = LocalDateSerializer.class)
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  @Column(length = 200)
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Column(nullable = false, length = 15)
  public String getContactNo() {
    return contactNo;
  }

  public void setContactNo(String contactNo) {
    this.contactNo = contactNo;
  }

  @Column
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Column
  public int getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(int postalCode) {
    this.postalCode = postalCode;
  }

  @Column
  public String getFbUserId() {
    return fbUserId;
  }

  public void setFbUserId(String fbUserId) {
    this.fbUserId = fbUserId;
  }

  @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
  public UserCredential getUserCredential() {
    return userCredential;
  }

  public void setUserCredential(UserCredential userCredential) {
    this.userCredential = userCredential;
  }

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "UserPermission", joinColumns = {
      @JoinColumn(name = "userId",referencedColumnName = "id")
  }, inverseJoinColumns = {
      @JoinColumn(name = "permissionId", referencedColumnName = "id")
  })
  public List<Permission> getPermissions() {
    return permissions;
  }

  public void setPermissions(
      List<Permission> permissions) {
    this.permissions = permissions;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    User user = (User) o;

    return uuid.equals(user.uuid);

  }

  @Override
  public int hashCode() {
    return uuid.hashCode();
  }
}
