package com.baizhi;

import com.baizhi.util.LuceneUtil;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class TestLucene {
    @Test
    public void test1() throws IOException {

        /*//创建索引库
        Directory dir = FSDirectory.open(new File("e:/index"));
        //新建分词器
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_44);
        IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_44,analyzer);
        //创建索引写入器
        IndexWriter indexWriter = new IndexWriter(dir, conf);*/
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        //新建article对应的document
        Document document = new Document();

        //field有八种数据类型加上string
        //java对象转docunment
        document.add(new StringField("id","3", Field.Store.NO));
        document.add(new StringField("name","144介绍", Field.Store.YES));
        document.add(new StringField("author","吴佳侬", Field.Store.YES));
        document.add(new TextField("content","班级里有些人，年龄特别大", Field.Store.YES));
        //写入
        indexWriter.addDocument(document);

        indexWriter.commit();
        indexWriter.close();
    }
    @Test
    public void test2() throws IOException {
        /*Directory dir = FSDirectory.open(new File("e:/index"));
        IndexReader indexReader = DirectoryReader.open(dir);
        //读取器
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);*/
        IndexSearcher indexSearcher = LuceneUtil.getIndexSearcher();
        //读取
        Query query = new TermQuery(new Term("name","1"));
        TopDocs search = indexSearcher.search(query, 10);
        ScoreDoc[] scoreDocs = search.scoreDocs;
        for (int i = 0; i < scoreDocs.length; i++) {
            ScoreDoc scoreDoc = scoreDocs[i];
            int doc = scoreDoc.doc;
            Document doc1 = indexSearcher.doc(doc);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println(doc1.get("id"));
            System.out.println(doc1.get("name"));
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
        }
    }
    @Test
    public void test3() throws IOException {
        //创建索引库
        Directory dir = FSDirectory.open(new File("e:/index"));
        //新建分词器
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_44);
        IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_44,analyzer);
        //创建索引写入器
        IndexWriter indexWriter = new IndexWriter(dir, conf);
        indexWriter.deleteDocuments(new Term("name","1"));
        indexWriter.commit();
        indexWriter.close();
    }
    @Test
    public void test4() throws IOException {
        //创建索引库
        Directory dir = FSDirectory.open(new File("e:/index"));
        //新建分词器
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_44);
        IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_44,analyzer);
        Document document = new Document();
        document.add(new StringField("id","1", Field.Store.YES));
        document.add(new StringField("title","144", Field.Store.YES));
        document.add(new StringField("author","吴佳侬", Field.Store.YES));
        document.add(new TextField("content","班级里有些人，年龄特别大,真的是很大", Field.Store.YES));
        //创建索引写入器
        IndexWriter indexWriter = new IndexWriter(dir, conf);
        indexWriter.updateDocument(new Term("id","1"),document);
        indexWriter.commit();
        indexWriter.close();
    }
}
