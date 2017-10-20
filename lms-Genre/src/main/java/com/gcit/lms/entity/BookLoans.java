package com.gcit.lms.entity;

import java.io.Serializable;
import java.util.Date;


public class BookLoans implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = -7489487618792990740L;
    private Integer bookId;
    private Integer branchId;
    private Integer cardNo;
    private Date dateOut;
    private Date dueDate;
    private Date dateIn;

    private Book book;
    private LibraryBranch branch;
    private Borrower borrower;
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
     * @return the cardNo
     */
    public Integer getCardNo() {
        return cardNo;
    }
    /**
     * @param cardNo the cardNo to set
     */
    public void setCardNo(Integer cardNo) {
        this.cardNo = cardNo;
    }
    /**
     * @return the dateOut
     */
    public Date getDateOut() {
        return dateOut;
    }
    /**
     * @param dateOut the dateOut to set
     */
    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
    }
    /**
     * @return the dueDate
     */
    public Date getDueDate() {
        return dueDate;
    }
    /**
     * @param dueDate the dueDate to set
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    /**
     * @return the dateIn
     */
    public Date getDateIn() {
        return dateIn;
    }
    /**
     * @param dateIn the dateIn to set
     */
    public void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
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
    /**
     * @return the borrower
     */
    public Borrower getBorrower() {
        return borrower;
    }
    /**
     * @param borrower the borrower to set
     */
    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
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
        result = prime * result + ((cardNo == null) ? 0 : cardNo.hashCode());
        result = prime * result + ((dateIn == null) ? 0 : dateIn.hashCode());
        result = prime * result + ((dateOut == null) ? 0 : dateOut.hashCode());
        result = prime * result + ((dueDate == null) ? 0 : dueDate.hashCode());
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
        BookLoans other = (BookLoans) obj;
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
        if (cardNo == null) {
            if (other.cardNo != null)
                return false;
        } else if (!cardNo.equals(other.cardNo))
            return false;
        if (dateIn == null) {
            if (other.dateIn != null)
                return false;
        } else if (!dateIn.equals(other.dateIn))
            return false;
        if (dateOut == null) {
            if (other.dateOut != null)
                return false;
        } else if (!dateOut.equals(other.dateOut))
            return false;
        if (dueDate == null) {
            if (other.dueDate != null)
                return false;
        } else if (!dueDate.equals(other.dueDate))
            return false;
        return true;
    }



}
