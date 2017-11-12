package poodle.util;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ArchiveUtils {
    private static Logger log = LoggerFactory.getLogger(ArchiveUtils.class);

    /**
     * Extracts files to the specified destination
     * @param file the file to extract to
     * @param dest the destination directory
     * @throws java.io.IOException
     */
    public static void unzipFileTo(String file, String dest) throws IOException {
        File target = new File(file);
        if (!target.exists())
            throw new IllegalArgumentException("Archive doesnt exist");
        FileInputStream fin = new FileInputStream(target);
        int BUFFER = 2048;
        byte data[] = new byte[BUFFER];

        if (file.endsWith(".zip") || file.endsWith(".jar")) {
            //getFromOrigin the zip file content
            ZipInputStream zis = new ZipInputStream(fin);
            //getFromOrigin the zipped file list entry
            ZipEntry ze = zis.getNextEntry();

            while (ze != null) {
                String fileName = ze.getName();

                File newFile = new File(dest + File.separator + fileName);

                if (ze.isDirectory()) {
                    newFile.mkdirs();
                    zis.closeEntry();
                    ze = zis.getNextEntry();
                    continue;
                }

                log.debug("file unzip : " + newFile.getAbsoluteFile());

                //create all non exists folders
                //else you will hit FileNotFoundException for compressed folder


                FileOutputStream fos = new FileOutputStream(newFile);

                int len;
                while ((len = zis.read(data)) > 0) {
                    fos.write(data, 0, len);
                }

                fos.flush();
                fos.close();
                zis.closeEntry();
                ze = zis.getNextEntry();
            }

            zis.close();


        }


        else if (file.endsWith(".tar")) {

            BufferedInputStream in = new BufferedInputStream(fin);
            untarData(dest, BUFFER, data, in);
        }

        else if (file.endsWith(".tar.gz") || file.endsWith(".tgz")) {

            BufferedInputStream in = new BufferedInputStream(fin);
            GzipCompressorInputStream gzIn = new GzipCompressorInputStream(in);
            untarData(dest, BUFFER, data, gzIn);
        }

        else if (file.endsWith(".gz")) {
            GZIPInputStream is2 = new GZIPInputStream(fin);
            File extracted = new File(target.getParent(), target.getName().replace(".gz", ""));
            if (extracted.exists())
                extracted.delete();
            extracted.createNewFile();
            OutputStream fos = FileUtils.openOutputStream(extracted);
            IOUtils.copyLarge(is2, fos);
            is2.close();
            fos.flush();
            fos.close();
        }



    }

    private static void untarData(String dest, int BUFFER, byte[] data, InputStream inS) throws IOException {
        TarArchiveInputStream tarIn = new TarArchiveInputStream(inS);

        TarArchiveEntry entry = null;

        /** Read the tar entries using the getNextEntry method **/

        while ((entry = (TarArchiveEntry) tarIn.getNextEntry()) != null) {

            log.debug("Extracting: " + entry.getName());

            /** If the entry is a directory, createComplex the directory. **/

            if (entry.isDirectory()) {

                File f = new File(dest + File.separator + entry.getName());
                f.mkdirs();
            }
            /**
             * If the entry is a file,write the decompressed file to the disk
             * and close destination stream.
             **/
            else {
                int count;

                FileOutputStream fos = new FileOutputStream(dest + File.separator + entry.getName());
                BufferedOutputStream destStream = new BufferedOutputStream(fos, BUFFER);
                try {

                    IOUtils.copy(tarIn, destStream);
                } finally {
                    IOUtils.closeQuietly(destStream);
                }
            }
        }


        /** Close the input stream **/

        tarIn.close();
    }

}
