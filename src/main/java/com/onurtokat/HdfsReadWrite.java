package com.onurtokat;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;


public class HdfsReadWrite {

    protected String hadoopPath;
    protected Configuration conf;
    protected FileSystem fs;

    public HdfsReadWrite(String hadoopPath){
        this.hadoopPath=hadoopPath;
        this.init();

    }

    protected void init(){
        conf=new Configuration();
        conf.addResource(new Path(hadoopPath+"/core-site.xml"));
        conf.addResource(new Path(hadoopPath+"/hdfs-site.xml"));
        conf.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
        conf.set("fs.file.impl", org.apache.hadoop.fs.LocalFileSystem.class.getName());

        try {
            fs = FileSystem.get(conf);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void write(String hdfsPath, String content) {
        Path path = new Path(hdfsPath);
        try{
            //delete file if exist
            if(fs.exists(path)){
                fs.delete(path, true);
            }

            //create file and write content to file
            FSDataOutputStream fos = fs.create(path);
            fos.writeUTF(content);
            fos.close();
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public String read(String hdfsPath) {
        String content = null;
        Path path = new Path(hdfsPath);
        try{
            //create file and write content to file
            FSDataInputStream fis = fs.open(path);

            //getWrappedStream() method for old hadoop hdfs version
            //normally, it sould be used with fis.getWrappedStream(), but it gives error, then I commented it.
            //BufferedReader reader = new BufferedReader(new InputStreamReader(fis.getWrappedStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            String s;
            while((s = reader.readLine()) != null){
                content += s;
            }
            reader.close();
            fis.close();
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return content;
    }

    public void copyFromLocal(String localPath, String hdfsPath){
        Path srcPath = new Path(localPath);
        Path destPath = new Path(hdfsPath);

        try{
            //check is path exist
            if(fs.exists(destPath)){
                System.out.println("No such destination " + destPath);
                return;
            }

            fs.copyFromLocalFile(srcPath, destPath);
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void delete(String hdfsPath) {
        Path path = new Path(hdfsPath);
        try{
            fs.delete(path, true);
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        String hadoopPath = "/etc/hadoop/conf.cloudera.hdfs";

        HdfsReadWrite hDFSOperation = new HdfsReadWrite(hadoopPath);
        //hDFSOperation.write("testing.txt", "hello world!");
        //System.out.println(hDFSOperation.read("testing-lagi.txt"));
        hDFSOperation.copyFromLocal("/home/cloudera/Downloads/trivago_case_study/data.dat", "data.dat");
        //hDFSOperation.delete("data.dat");
    }
}
