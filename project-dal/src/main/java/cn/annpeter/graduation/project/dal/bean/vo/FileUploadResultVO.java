package cn.annpeter.graduation.project.dal.bean.vo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created on 2017/03/12
 *
 * @author annpeter.it@gmail.com
 */
public class FileUploadResultVO extends LinkedList<FileUploadResultVO.FileItem> implements Serializable {

    public void addFileItem(String filename, String fileUrl) {
        this.add(new FileItem(filename, fileUrl));
    }

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    public static class FileItem {
        private String filename;
        private String fileUrl;

        public FileItem(String filename, String fileUrl) {
            this.filename = filename;
            this.fileUrl = fileUrl;
        }
    }

}
