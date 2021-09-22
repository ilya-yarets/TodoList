package entity;

import constants.Constants;

import java.sql.Date;
import java.util.Objects;

public class ToDo {
    private Integer id;
    private Date dateToDo;
    private String nameToDo;
    private Byte statusToDo;
    private String fileNameToDo;
    private int userId;

    public ToDo() {
        nameToDo = Constants.EMPTY_LINE;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateToDo() {
        return dateToDo;
    }

    public void setDateToDo(Date dateToDo) {
        this.dateToDo = dateToDo;
    }

    public String getNameToDo() {
        return nameToDo;
    }

    public void setNameToDo(String nameToDo) {
        this.nameToDo = nameToDo;
    }

    public Byte getStatusToDo() {
        return statusToDo;
    }

    public void setStatusToDo(Byte statusToDo) {
        this.statusToDo = statusToDo;
    }

    public String getFileNameToDo() {
        return fileNameToDo;
    }

    public void setFileNameToDo(String fileNameToDo) {
        this.fileNameToDo = fileNameToDo;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ToDo)) return false;
        ToDo toDo = (ToDo) o;
        return getUserId() == toDo.getUserId() &&
                getId().equals(toDo.getId()) &&
                getDateToDo().equals(toDo.getDateToDo()) &&
                getNameToDo().equals(toDo.getNameToDo()) &&
                getStatusToDo().equals(toDo.getStatusToDo()) &&
                getFileNameToDo().equals(toDo.getFileNameToDo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDateToDo(), getNameToDo(), getStatusToDo(), getFileNameToDo(), getUserId());
    }

    @Override
    public String toString() {
        return "ToDo{id=" + id + ", dateToDo=" + dateToDo + ", nameToDo='" + nameToDo + "', statusToDo=" + statusToDo +
                ", fileNameToDo='" + fileNameToDo + "', userId=" + userId + '}';
    }
}