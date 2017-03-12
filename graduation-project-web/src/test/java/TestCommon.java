import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static cn.annpeter.graduation.project.web.model.WebConstants.File_UPLOAD_BASE_DIR;

/**
 * Created on 2017/03/12
 *
 * @author annpeter.it@gmail.com
 */
public class TestCommon {
    @Test
    public void getDateTest() throws Exception {
        System.out.println(DateFormatUtils.ISO_DATE_FORMAT.format(new Date()));
    }

    @Test
    public void mkDirTest() throws Exception {
        System.out.println(getUseAbleFile("BB", "AA", "png"));
    }


    private File getUseAbleFile(String fileUri, String filename, String suffix) throws IOException {
        String filePath = File_UPLOAD_BASE_DIR + fileUri;
        File localFile = new File(filePath, filename + "." + suffix);
        localFile.getParentFile().mkdirs();
        if (localFile.createNewFile()) {
            return localFile;
        } else {
            return getUseAbleFile(fileUri, filename + "_" + RandomStringUtils.randomNumeric(4), suffix);
        }
    }
}
