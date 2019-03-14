package com.baizhi.dao;

import com.baizhi.entity.Commodity;
import com.baizhi.util.LuceneUtil;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LuceneDaoImpl implements LuceneDao{
    @Override
    public void inser(Commodity commodity) {
        System.out.println(commodity.getId()+"sss"+commodity.getName());
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        Document document = new Document();
        document.add(new StringField("id",commodity.getId(), Field.Store.YES));
        document.add(new TextField("name",commodity.getName(), Field.Store.YES));
        document.add(new TextField("price",commodity.getPrice(), Field.Store.YES));
        document.add(new TextField("describe",commodity.getDescribe(), Field.Store.YES));
        document.add(new TextField("picture",commodity.getPicture(), Field.Store.YES));
        document.add(new TextField("status",commodity.getStatus(), Field.Store.YES));
        document.add(new TextField("datamanufacture",commodity.getDatamanufacture(), Field.Store.YES));
        document.add(new TextField("origin",commodity.getOrigin(), Field.Store.YES));
        //写入
        try {
            indexWriter.addDocument(document);
            indexWriter.commit();
            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Commodity> queryall(String name) {
        List<Commodity> commodities = new ArrayList<Commodity>();
        IndexSearcher indexSearcher = LuceneUtil.getIndexSearcher();
        //读取
        Query query = new TermQuery(new Term("name",name));
        try {
            TopDocs search = indexSearcher.search(query, 10);
            ScoreDoc[] scoreDocs = search.scoreDocs;
            for (int i = 0; i < scoreDocs.length; i++) {
                ScoreDoc scoreDoc = scoreDocs[i];
                int doc = scoreDoc.doc;
                Document doc1 = indexSearcher.doc(doc);
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println(doc1.get("id"));
                System.out.println(doc1.get("name"));
                System.out.println(doc1.get("price"));
                System.out.println(doc1.get("describe"));
                System.out.println(doc1.get("picture"));
                System.out.println(doc1.get("status"));
                System.out.println(doc1.get("datamanufacture"));
                System.out.println(doc1.get("origin"));
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
                commodities.add(new Commodity(doc1.get("id"),doc1.get("name"),doc1.get("price"),doc1.get("describe"),doc1.get("picture"),doc1.get("status"),doc1.get("datamanufacture"),doc1.get("origin")));
            }
            System.out.println(commodities);
            return commodities;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
