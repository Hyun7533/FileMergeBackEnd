package com.example.demo.CTL;

public class FileVO {

    private byte[] file1;

    private byte[] file2;

    public FileVO(byte[] file1, byte[] file2) {
        this.file1 = file1;
        this.file2 = file2;
    }

    public byte[] getFile1() {
        return file1;
    }

    public void setFile1(byte[] file1) {
        this.file1 = file1;
    }

    public byte[] getFile2() {
        return file2;
    }

    public void setFile2(byte[] file2) {
        this.file2 = file2;
    }
}
