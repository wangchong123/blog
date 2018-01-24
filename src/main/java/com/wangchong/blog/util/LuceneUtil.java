package com.wangchong.blog.util;

import com.wangchong.blog.entity.Article;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.StringReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class LuceneUtil {
    //索引存放位置
    private static final String PATH = "d:\\index";
    //
    private static final String[] FIELDS = {"title","content","describe"};

    /**
     * 生成索引
     * @param list
     */
    public static void createIndex(List<Article> list){
        if(list == null || list.size() == 0){
            return;
        }
        List<Document> docList = new ArrayList<Document>();
        Document doc;
        for (Article article : list) {
            doc = createDocument(article);
            docList.add(doc);
        }
        try {
            Analyzer analyzer = new SmartChineseAnalyzer();
            Directory directory = FSDirectory.open(new File(PATH).toPath());
            IndexWriterConfig config = new IndexWriterConfig(analyzer).setOpenMode(IndexWriterConfig.OpenMode.CREATE);
            IndexWriter writer = new IndexWriter(directory,config);
            writer.addDocuments(docList);
            writer.close();
            directory.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成索引
     * @param article
     */
    public static void createIndex(Article article){
        if(article == null ){
            return;
        }
        Document doc = createDocument(article);
        try {
            //获取中文分词器
            Analyzer analyzer = new SmartChineseAnalyzer();
            //索引目录
            Directory directory = FSDirectory.open(new File(PATH).toPath());
            //OpenMode.APPEND:增量索引
            //OpenMode.CREATE:覆盖
            IndexWriterConfig config = new IndexWriterConfig(analyzer).setOpenMode(IndexWriterConfig.OpenMode.APPEND);
            IndexWriter writer = new IndexWriter(directory,config);
            writer.addDocument(doc);
            writer.close();
            directory.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 删除全部索引
     */
    public static void deleteIndex(){
        try {
            Analyzer analyzer = new SmartChineseAnalyzer();
            Directory directory = FSDirectory.open(new File(PATH).toPath());
            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            IndexWriter writer = new IndexWriter(directory,config);
            writer.deleteAll();
            writer.close();
            directory.close();
        }catch (Exception e){

        }
    }

    /**
     * 生成document
     * @param article
     * @return
     */
   public static Document createDocument(Article article){
        if(article == null){
            return null;
        }
       Document doc = new Document();
       Field id = new LongField("id",article.getId(),Field.Store.YES);
       Field title = new TextField("title",article.getTitle(), Field.Store.YES);
       Field describe = new TextField("describe",article.getDescribe(), Field.Store.YES);
       Field content = new TextField("content",article.getContent(), Field.Store.YES);
       doc.add(title);
       doc.add(describe);
       doc.add(content);
       doc.add(id);
       return doc;
   }

    /**
     * 查询
     * @param source
     */
    public static void search(String source){
        try {
            Directory directory = FSDirectory.open(Paths.get(PATH));
            IndexReader reader = DirectoryReader.open(directory);
            IndexSearcher searcher = new IndexSearcher(reader);
            Analyzer analyzer = new SmartChineseAnalyzer();
            //从多个fields中查找
            QueryParser parser = new MultiFieldQueryParser(FIELDS,analyzer);
            Query query = parser.parse(source);
            //高亮显示
            SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<b><font color=red>","</font></b>");
            QueryScorer scorer = new QueryScorer(query);//计算得分
            Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);//根据得分计算片段
            Highlighter highlighter = new Highlighter(formatter,scorer);
            highlighter.setTextFragmenter(fragmenter);//设置要显示的片段
            TopDocs docs = searcher.search(query,10);
            for (ScoreDoc score:docs.scoreDocs){
                Document document = searcher.doc(score.doc);
                String title = document.get("title");
                System.out.println(document.get("id"));
                //显示高亮部分
                if(title != null){
                    TokenStream tokenStream = analyzer.tokenStream("title",new StringReader(title));
                    String htitle = highlighter.getBestFragment(tokenStream,title);
                    System.out.println(htitle);
                }
            }
        }catch (Exception e){

        }
    }

    public static void updateIndex(Article article){
        if(article != null){
            // Document doc = createDocument(article);
            try {
                Analyzer analyzer = new SmartChineseAnalyzer();
                Directory directory = FSDirectory.open(new File(PATH).toPath());
                IndexWriterConfig config = new IndexWriterConfig(analyzer);
                IndexWriter writer = new IndexWriter(directory,config);
                //试了下 至少要写出下面中的三个term才能删除，只写一个id不行，原因未知。。。
                writer.deleteDocuments(new Term("key","10"));
               /* writer.deleteDocuments(new Term("title","标题2"));
                writer.deleteDocuments(new Term("describe","描述"));
                writer.deleteDocuments(new Term("content","内容"));*/
                writer.close();
                directory.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
       Article a = new Article();
       a.setTitle("标题2");
       a.setDescribe("描述");
       a.setContent("内容");
       a.setId(10l);
       //createIndex(a);
       search("标题");
      // updateIndex(a);
       // deleteIndex();
    }
}
