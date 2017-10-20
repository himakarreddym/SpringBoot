package com.gcit.lms.entity;

import java.io.Serializable;

public class BookCopies implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2835656259727502399L;
    private Integer bookId;
    private Integer branchId;
    private Integer copies;

    private Book book;
    private LibraryBranch branch;


    /**
     * @return the bookId
     */
    public Integer getBookId() {
        return bookId;
    }
    /**
     * @param bookId the bookId to set
     */
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
    /**
     * @return the branchId
     */
    public Integer getBranchId() {
        return branchId;
    }
    /**
     * @param branchId the branchId to set
     */
    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }
    /**
     * @return the copies
     */
    public Integer getCopies() {
        return copies;
    }
    /**
     * @param copies the copies to set
     */
    public void setCopies(Integer copies) {
        this.copies = copies;
    }
    /**
     * @return the book
     */
    public Book getBook() {
        return book;
    }
    /**
     * @param book the book to set
     */
    public void setBook(Book book) {
        this.book = book;
    }
    /**
     * @return the branch
     */
    public LibraryBranch getBranch() {
        return branch;
    }
    /**
     * @param branch the branch to set
     */
    public void setBranch(LibraryBranch branch) {
        this.branch = branch;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
        result = prime * result + ((branchId == null) ? 0 : branchId.hashCode());
        result = prime * result + ((copies == null) ? 0 : copies.hashCode());
        return result;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BookCopies other = (BookCopies) obj;
        if (bookId == null) {
            if (other.bookId != null)
                return false;
        } else if (!bookId.equals(other.bookId))
            return false;
        if (branchId == null) {
            if (other.branchId != null)
                return false;
        } else if (!branchId.equals(other.branchId))
            return false;
        if (copies == null) {
            if (other.copies != null)
                return false;
        } else if (!copies.equals(other.copies))
            return false;
        return true;
    }


}
