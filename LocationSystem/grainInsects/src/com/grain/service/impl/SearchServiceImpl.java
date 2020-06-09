package com.grain.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.util.Version;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.grain.Page;
import com.grain.Pageable;
import com.grain.dao.ArticleDao;
import com.grain.dao.TCatalogIndexDao;
import com.grain.entity.Article;
import com.grain.entity.TCatalogIndex;
import com.grain.entity.TCatalogIndex.OrderType;
import com.grain.service.SearchService;

/**
 * Service - 搜索
 * @author Mark
 *
 */
@Service("searchServiceImpl")
@Transactional
public class SearchServiceImpl implements SearchService {

	/** 模糊查询最小相似度 */
	private static final float FUZZY_QUERY_MINIMUM_SIMILARITY = 0.5F;

	@PersistenceContext
	protected EntityManager entityManager;
	
	@Resource(name = "articleDaoImpl")
	private ArticleDao articleDao;
	@Resource(name = "tcatalogIndexDaoImpl")
	private TCatalogIndexDao catalogDao;

	public void index() {
		index(Article.class);
		index(TCatalogIndex.class);
	}

	public void index(Class<?> type) {
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
		if (type == Article.class) {
			for (int i = 0; i < articleDao.count(); i += 20) {
				List<Article> articles = articleDao.findList(i, 20, null, null);
				for (Article article : articles) {
					fullTextEntityManager.index(article);
				}
				fullTextEntityManager.flushToIndexes();
				fullTextEntityManager.clear();
				articleDao.clear();
			}
		} else if (type == TCatalogIndex.class) {
			for (int i = 0; i < catalogDao.count(); i += 20) {
				List<TCatalogIndex> products = catalogDao.findList(i, 20, null, null);
				for (TCatalogIndex product : products) {
					fullTextEntityManager.index(product);
				}
				fullTextEntityManager.flushToIndexes();
				fullTextEntityManager.clear();
				catalogDao.clear();
			}
		}
	}

	public void index(Article article) {
		if (article != null) {
			FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
			fullTextEntityManager.index(article);
		}
	}

	public void index(TCatalogIndex product) {
		if (product != null) {
			FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
			fullTextEntityManager.index(product);
		}
	}

	public void purge() {
		purge(Article.class);
		purge(TCatalogIndex.class);
	}

	public void purge(Class<?> type) {
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
		if (type == Article.class) {
			fullTextEntityManager.purgeAll(Article.class);
		} else if (type == TCatalogIndex.class) {
			fullTextEntityManager.purgeAll(TCatalogIndex.class);
		}
	}

	public void purge(Article article) {
		if (article != null) {
			FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
			fullTextEntityManager.purge(Article.class, article.getId());
		}
	}

	public void purge(TCatalogIndex product) {
		if (product != null) {
			FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
			fullTextEntityManager.purge(TCatalogIndex.class, product.getId());
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Page<Article> search(String keyword, Pageable pageable) {
		if (StringUtils.isEmpty(keyword)) {
			return new Page<Article>();
		}
		if (pageable == null) {
			pageable = new Pageable();
		}
		Page<Article> catalogIndexs = articleDao.findArticleByKeyword(keyword, pageable);
		return catalogIndexs;
		/*try {
			String text = QueryParser.escape(keyword);
			QueryParser titleParser = new QueryParser(Version.LUCENE_35, "title", new IKAnalyzer());
			titleParser.setDefaultOperator(QueryParser.AND_OPERATOR);
			Query titleQuery = titleParser.parse(text);
			FuzzyQuery titleFuzzyQuery = new FuzzyQuery(new Term("title", text), FUZZY_QUERY_MINIMUM_SIMILARITY);
			Query contentQuery = new TermQuery(new Term("content", text));
			Query isPublicationQuery = new TermQuery(new Term("isPublication", "true"));
			BooleanQuery textQuery = new BooleanQuery();
			BooleanQuery query = new BooleanQuery();
			textQuery.add(titleQuery, Occur.SHOULD);
			textQuery.add(titleFuzzyQuery, Occur.SHOULD);
			textQuery.add(contentQuery, Occur.SHOULD);
			query.add(isPublicationQuery, Occur.MUST);
			query.add(textQuery, Occur.MUST);
			FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
			FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query, Article.class);
			fullTextQuery.setSort(new Sort(new SortField[] { new SortField("isTop", SortField.STRING, true), new SortField(null, SortField.SCORE), new SortField("createDate", SortField.LONG, true) }));
			fullTextQuery.setFirstResult((pageable.getPage() - 1) * pageable.getRows());
			fullTextQuery.setMaxResults(pageable.getRows());
			return new Page<Article>(fullTextQuery.getResultList(), fullTextQuery.getResultSize(), pageable);
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
//		return new Page<Article>();
	}

	@Transactional(readOnly = true)
	public Page<TCatalogIndex> search(String keyword, OrderType orderType, Pageable pageable) {
		
		return null;
	}
	
	@Transactional(readOnly = true)
	public Page<TCatalogIndex> searchCatalogIndex(String keyword, Pageable pageable) {
		
		if(StringUtils.isEmpty(keyword)){
			return null;
		}
		if (pageable == null) {
			pageable = new Pageable();
		}
		Page<TCatalogIndex> catalogIndexs = catalogDao.findCatalogIndexByKeyword(keyword, pageable);
		return catalogIndexs;
	}

}