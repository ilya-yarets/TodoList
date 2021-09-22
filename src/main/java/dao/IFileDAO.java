package dao;

public interface IFileDAO {
    boolean updateFilePath(int id, String filename, String pathFile);
}