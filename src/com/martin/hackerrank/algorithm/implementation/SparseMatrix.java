package com.martin.hackerrank.algorithm.implementation;

public class SparseMatrix
{
    private int N;
    private SparseArray sparsearray[];
 
    public SparseMatrix(int N)
    {
        this.N = N;
        sparsearray = new SparseArray[N];
        for (int index = 0; index < N; index++)
        {
            sparsearray[index] = new SparseArray(N);
        }
    }
 
    public void store(int rowindex, int colindex, Object value)
    {
        if (rowindex < 0 || rowindex > N)
            throw new RuntimeException("row index out of bounds");
        if (colindex < 0 || colindex > N)
            throw new RuntimeException("col index out of bounds");
        sparsearray[rowindex].store(colindex, value);
    }
 
    public Object get(int rowindex, int colindex)
    {
        if (rowindex < 0 || colindex > N)
            throw new RuntimeException("row index out of bounds");
        if (rowindex < 0 || colindex > N)
            throw new RuntimeException("col index out of bounds");
 
        return (sparsearray[rowindex].fetch(colindex));
    }
 
    public static void main(String... arg)
    {
        Integer[][] iarray = new Integer[3][3];
        iarray[0][0] = 1;
        iarray[0][1] = null;
        iarray[0][2] = 2;
        iarray[1][0] = null;
        iarray[1][1] = 3;
        iarray[1][2] = null;
        iarray[2][0] = 4;
        iarray[2][1] = 6;
        iarray[2][2] = null;
 
        SparseMatrix sparseMatrix = new SparseMatrix(3);
        for (int rowindex = 0; rowindex < 3; rowindex++)
        {
            for (int colindex = 0; colindex < 3; colindex++)
            {
                sparseMatrix.store(rowindex, colindex,
                iarray[rowindex][colindex]);
            }
        }
        System.out.println("the sparse Matrix is ");
        for (int rowindex = 0; rowindex < 3; rowindex++)
        {
            for (int colindex = 0; colindex < 3; colindex++)
            {
                System.out.print(sparseMatrix.get(rowindex, colindex) + "\t");
            }
            System.out.println();
        }
    }
}
 
class List
{
    private int index;
    private Object value;
    private List nextindex;
 
    public List(int index)
    {
        this.index = index;
        nextindex = null;
        value = null;
    }
 
    public List()
    {
        index = -1;
        value = null;
        nextindex = null;
    }
 
    public void store(int index, Object value)
    {
        List current = this;
        List previous = null;
 
        List node = new List(index);
        node.value = value;
        while (current != null && current.index < index)
        {
            previous = current;
            current = current.nextindex;
        }
        if (current == null)
        {
            previous.nextindex = node;
        }
        else
        {
            if (current.index == index)
            {
                System.out.println("DUPLICATE INDEX");
                return;
            }
            previous.nextindex = node;
            node.nextindex = current;
        }
        return;
    }
 
    public Object fetch(int index)
    {
        List current = this;
        Object value = null;
        while (current != null && current.index != index)
        {
            current = current.nextindex;
        }
        if (current != null)
        {
            value = current.value;
        }
        else
        {
            value = null;
        }
        return value;
    }
 
    public int elementCount()
    {
        int elementCount = 0;
        for (List current = this.nextindex; (current != null); current = current.nextindex)
        {
            elementCount++;
        }
        return elementCount;
    }
}
 
 class SparseArray
{
    private List start;
    private int index;
 
    SparseArray(int index)
    {
        start = new List();
        this.index = index;
    }
 
    public void store(int index, Object value)
    {
        if (index >= 0 && index < this.index)
        {
            if (value != null)
                start.store(index, value);
        } else
        {
            System.out.println("INDEX OUT OF BOUNDS");
        }
    }
 
    public Object fetch(int index)
    {
        if (index >= 0 && index < this.index)
            return start.fetch(index);
        else
        {
            System.out.println("INDEX OUT OF BOUNDS");
            return null;
        }
    }
 
    public int elementCount()
    {
        return start.elementCount();
    }
}