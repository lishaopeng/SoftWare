/*    */ package com.portal.datacenter.lucene;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.List;

/*    */ import org.apache.lucene.index.FilterIndexReader;
/*    */ import org.apache.lucene.index.IndexReader;
/*    */ import org.apache.lucene.index.Term;
/*    */ import org.apache.lucene.index.TermDocs;
/*    */ import org.apache.lucene.util.OpenBitSet;
/*    */ 
/*    */ 
/*    */ public class MyFilterIndexReader extends FilterIndexReader
/*    */ {
/*    */   OpenBitSet dels;
/*    */ 
/*    */   public MyFilterIndexReader(IndexReader in)
/*    */   {
/* 17 */     super(in);
/* 18 */     this.dels = new OpenBitSet(in.maxDoc());
/*    */   }
/*    */ 
/*    */   public MyFilterIndexReader(IndexReader in, List<String> idToDelete) throws IOException
/*    */   {
/* 23 */     super(in);
/* 24 */     this.dels = new OpenBitSet(in.maxDoc());
/* 25 */     for (String id : idToDelete) {
/* 26 */       TermDocs td = in.termDocs(new Term("id", id));
/* 27 */       if (td.next())
/* 28 */         this.dels.set(td.doc());
/*    */     }
/*    */   }
/*    */ 
/*    */   @Override
public int numDocs()
/*    */   {
/* 34 */     return this.in.numDocs() - (int)this.dels.cardinality();
/*    */   }
/*    */ 
/*    */   @Override
public TermDocs termDocs(Term term) throws IOException {
/* 38 */     return new FilterIndexReader.FilterTermDocs(this.in.termDocs(term))
/*    */     {
/*    */       @Override
public boolean next()
/*    */         throws IOException
/*    */       {
/*    */         boolean res;
/* 41 */         while ((res = super.next()))
/*    */         {
/* 42 */           if (!MyFilterIndexReader.this.dels.get(doc())) {
/*    */             break;
/*    */           }
/*    */         }
/* 46 */         return res;
/*    */       }
/*    */     };
/*    */   }
/*    */ 
/*    */   @Override
public TermDocs termDocs() throws IOException {
/* 52 */     return new FilterIndexReader.FilterTermDocs(this.in.termDocs())
/*    */     {
/*    */       @Override
public boolean next()
/*    */         throws IOException
/*    */       {
/*    */         boolean res;
/* 55 */         while ((res = super.next()))
/*    */         {
/* 56 */           if (!MyFilterIndexReader.this.dels.get(doc())) {
/*    */             break;
/*    */           }
/*    */         }
/* 60 */         return res;
/*    */       }
/*    */     };
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.lucene.MyFilterIndexReader
 * JD-Core Version:    0.6.1
 */