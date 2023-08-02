/*
package com.Utils.Hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

*/
/**
 * @author SAIDULU (EMPID:061)
 * @version 1.0
 * 01-Jun-2016
 *//*

@Component
public class HibernateUtils*/
/* extends OLPExceptionHandler*//*
 {
    Logger logger =  LogManager.getLogger("HibernateUtils");

    @Autowired
    public SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        try {

            SessionFactory sessionFactory = getSessionFactory();
			*/
/*if(UtilityConstants.INTERCEPTORS.ISHIBERNATEINTERCEPTORENABLED)
				return sessionFactory.withOptions().interceptor(auditLogInterceptor).openSession();
			else*//*

            return sessionFactory.openSession();
            //logger.info("getSession() : Hibernate Session Object ->> "+session);
            //return session;
        }catch(Exception e) {
            logger.error("(:) Error While Getting Session Object (:)");
            e.printStackTrace();
        }
        return null;
    }

    public Session openSession() {

        try {

            SessionFactory sessionFactory = getSessionFactory();
            return sessionFactory.openSession();
        }catch(Exception e) {
            logger.error("(:) Error While Getting Session Object (:) in openSession()");
            e.printStackTrace();
        }
        return null;

    }

    public void closeSession(Session session) {
        //logger.info("closeSession() : Session Object ->> "+session);
        if(session != null) {
            session.close();
        }
    }

    public <T> T saveEntity(T entity) {

        logger.info("saveEntity() : Entity Class ->> "+entity.getClass());

        Session session = null;
        Transaction tx = null;
        try {
            session = getSession();
            tx = session.beginTransaction();
            session.save(entity);
            tx.commit();
            return entity;

        }catch(Exception e) {
            logger.error("(:) Error in SaveEntity Method (:) ");
            e.printStackTrace();
            if(tx != null )
                tx.rollback();
        }finally {
            closeSession(session);
        }
        return null;
    }

    public <T> T updateEntity(T entity) {

        logger.info("updateEntity() : Entity Class ->> "+entity.getClass());
        Transaction tx =null;
        Session session = null;
        try {
            session = getSession();
            tx = session.beginTransaction();
            session.update(entity);
            tx.commit();
            return entity;

        }catch(Exception e) {
            logger.error("(:) Error in UpdateEntity Method (:) ");
            e.printStackTrace();
            if( tx != null )
                tx.rollback();
        }finally {
            closeSession(session);
        }
        return null;
    }

    public <T> T saveOrUpdateEntity(T entity) {

        logger.info("saveOrUpdateEntity() : Entity Class ->> "+entity.getClass());
        Transaction tx = null;
        Session session = null;
        try {
            session = getSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(entity);
            tx.commit();
            return entity;

        }catch(Exception e) {
            logger.error("(:) Error in SaveOrUpdateEntity Method (:) ");
            e.printStackTrace();
            if( tx != null )
                tx.rollback();
        }finally {
            closeSession(session);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> T deleteEntity(T entity, Serializable primaryId) {

        logger.info("deleteEntity() : Entity Class ->> "+entity.getClass());
        Transaction tx = null;
        Session session = null;
        try {
            session = getSession();
            tx = session.beginTransaction();
            Object dataObject = session.get(entity.getClass(),primaryId);
            session.delete((T)dataObject);
            tx.commit();
            return entity;

        }catch(Exception e) {
            logger.error("(:) Error in DeleteEntity Method (:) ");
            if( tx != null )
                tx.rollback();
            e.printStackTrace();
        }finally {
            closeSession(session);
        }
        return null;
    }

    public String deleteEntityByHQL(String hqlQuery) {

        logger.info("deleteEntityByCriteria() : HQL Query ->> "+hqlQuery);

        Session session = null;
        try {
            session = getSession();
            Query query = session.createQuery(hqlQuery);
            query.executeUpdate();
            return "SUCCESS"; //TODO : Need to uncomment above line
        }catch(Exception e) {
            logger.error("(:) Error in deleteEntityByHQL Method (:)");
            e.printStackTrace();
        }
        finally {
            closeSession(session);
        }
        return null;
    }

    public String deleteEntityByHQL(String hqlQuery,String setParamName, Collection setParamValue) {

        logger.info("deleteEntityByCriteria() : HQL Query ->> "+hqlQuery+" set PropertyName"+setParamName+" setParamValues"+setParamValue);

        Session session = null;
        try {
            session = getSession();
            Query query = session.createQuery(hqlQuery);
            query.setParameterList(setParamName, setParamValue);
            query.executeUpdate();
            return "SUCCESS"; //TODO : Need to uncomment above line
        }catch(Exception e) {
            logger.error("(:) Error in deleteEntityByHQL Method (:)");
            e.printStackTrace();
        }
        finally {
            closeSession(session);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> T findEntityById(Class<T> entityClass,Serializable primaryId) {

        logger.info("getEntityById() : Entity Class ->> "+entityClass+" Primary ID ->>"+primaryId);

        Session session = null;
        try {
            session = getSession();
            Object dataObject = session.get(entityClass, primaryId);
            return (T) dataObject;
        }catch(Exception e) {
            logger.error("(:) Error in getEntityById Method (:)");
            e.printStackTrace();
        }
        finally {
            closeSession(session);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> T findEntityByCriteria(Class<T> entityClass,String primaryPropertyName, Serializable primaryId) {

        logger.info("getEntityByCriteria() : Entity Class ->> "+entityClass+" PrimaryPropertyName ->> "+primaryPropertyName+" Primary ID ->>"+primaryId);

        Session session = null;
        try {
            session = getSession();
            Object dataObject =  session.createCriteria(entityClass)
                    .add(Restrictions.eq(primaryPropertyName, primaryId))
                    .uniqueResult();
            return (T) dataObject;
        }catch(Exception e) {
            logger.error("(:) Error in GetEntityByCriteria Method (:)");
            e.printStackTrace();
        }
        finally {
            closeSession(session);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> T findEntityByCriteria(Class<T> entityClass,String primaryPropertyName1,String primaryPropertyName2, Serializable primaryId1,Serializable primaryId2) {

        logger.info("getEntityByCriteria() : Entity Class ->> "+entityClass+" PrimaryPropertyNames ->> "+primaryPropertyName1+" & "+primaryPropertyName2+" Primary IDs ->>"+primaryId1+" & "+primaryId2);

        Session session = null;
        try {
            session = getSession();
            Object dataObject =  session.createCriteria(entityClass)
                    .add(Restrictions.eq(primaryPropertyName1, primaryId1))
                    .add(Restrictions.eq(primaryPropertyName2, primaryId2))
                    .uniqueResult();
            return (T) dataObject;
        }catch(Exception e) {
            logger.error("(:) Error in GetEntityByCriteria Method two columns (:)");
            e.printStackTrace();
        }
        finally {
            closeSession(session);
        }
        return null;
    }



    @SuppressWarnings("unchecked")
    public <T> T findEntityByCriteria(Class<T> entityClass, String primaryPropertyName1, String primaryPropertyName2, String primaryPropertyName3, Serializable primaryId1, Serializable primaryId2, Serializable primaryId3) {

        logger.info("getEntityByCriteria() : Entity Class ->> "+entityClass+" PrimaryPropertyNames ->> "+primaryPropertyName1+" & "+primaryPropertyName2+" & "+primaryPropertyName3+" Primary IDs ->>"+primaryId1+" & "+primaryId2+" & "+primaryId3);

        Session session = null;
        try {
            session = getSession();
            Object dataObject =  session.createCriteria(entityClass)
                    .add(Restrictions.eq(primaryPropertyName1, primaryId1))
                    .add(Restrictions.eq(primaryPropertyName2, primaryId2))
                    .add(Restrictions.eq(primaryPropertyName3, primaryId3))
                    .uniqueResult();
            return (T) dataObject;
        }catch(Exception e) {
            logger.error("(:) Error in GetEntityByCriteria Method two columns (:)");
            e.printStackTrace();
        }
        finally {
            closeSession(session);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> T findEntityByOrCriteria(Class<T> entityClass,String primaryPropertyName1,String primaryPropertyName2, Serializable primaryId1,Serializable primaryId2) {

        logger.info("getEntityByCriteria() : Entity Class ->> "+entityClass+" PrimaryPropertyNames ->> "+primaryPropertyName1+" | "+primaryPropertyName2+" Primary IDs ->>"+primaryId1+" | "+primaryId2);

        Session session = null;
        try {
            session = getSession();
            Object dataObject =  session.createCriteria(entityClass)
                    .add(Restrictions.or(Restrictions.eq(primaryPropertyName1, primaryId1),
                            Restrictions.eq(primaryPropertyName2, primaryId2)))
                    .uniqueResult();
            return (T) dataObject;
        }catch(Exception e) {
            logger.error("(:) Error in GetEntityByCriteria Method two columns (:)");
            e.printStackTrace();
        }
        finally {
            closeSession(session);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> loadEntitiesByCriteria(Class<T> entityClass) {

        logger.info("loadEntitiesByCriteria() : Entity Class ->> "+entityClass);

        Session session = null;
        try {
            session = getSession();
            return (List<T>) session.createCriteria(entityClass).list();
        }catch(Exception e) {
            logger.error("(:) Error in LoadEntitiesByCriteria Method (:)");
            e.printStackTrace();
        }
        finally {
            closeSession(session);
        }
        return null;
    }


    @SuppressWarnings("unchecked")
    public <T> List<T> loadEntitiesByCriteria(Class<T> entityClass,String primaryPropertyName1,String primaryPropertyName2, Serializable primaryId1,Serializable primaryId2) {

        logger.info("loadEntitiesByCriteria() : Entity Class ->> "+entityClass+" PrimaryPropertyNames ->> "+primaryPropertyName1+" & "+primaryPropertyName2+" Primary IDs ->>"+primaryId1+" & "+primaryId2);

        Session session = null;
        try {
            session = getSession();
            return (List<T>) session.createCriteria(entityClass)
                    .add(Restrictions.eq(primaryPropertyName1, primaryId1))
                    .add(Restrictions.eq(primaryPropertyName2, primaryId2))
                    .list();
        }catch(Exception e) {
            logger.error("(:) Error in loadEntitiesByCriteria Method two columns (:)");
            e.printStackTrace();
        }
        finally {
            closeSession(session);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> loadEntitiesByCriteria(Class<T> entityClass,String primaryPropertyName1,String primaryPropertyName2, String primaryPropertyName3, Serializable primaryId1,Serializable primaryId2, String primaryId3) {

        logger.info("loadEntitiesByCriteria() : Entity Class ->> "+entityClass+" PrimaryPropertyNames ->> "+primaryPropertyName1+" & "+primaryPropertyName2+" & "+primaryPropertyName3+", Primary IDs ->>"+primaryId1+" & "+primaryId2+" & "+primaryId3);

        Session session = null;
        try {
            session = getSession();
            return (List<T>) session.createCriteria(entityClass)
                    .add(Restrictions.eq(primaryPropertyName1, primaryId1))
                    .add(Restrictions.eq(primaryPropertyName2, primaryId2))
                    .add(Restrictions.eq(primaryPropertyName3, primaryId3))
                    .list();
        }catch(Exception e) {
            logger.error("(:) Error in loadEntitiesByCriteria Method two columns (:)");
            e.printStackTrace();
        }
        finally {
            closeSession(session);
        }
        return null;
    }


    @SuppressWarnings("unchecked")
    public <T> List<T> loadEntitiesByCriteria(Class<T> entityClass, List<ProjectionsDTO> projectionsList, List<RestrictionsDTO> restrictionsList) {

        logger.info("loadEntitiesByCriteria() : Entity Class ->> "+entityClass+" ProjectionsList ->> "+projectionsList);

        Session session = null;
        try {
            session = getSession();

            ProjectionList projectionList = Projections.projectionList();
            projectionsList.forEach( (projectionsdto) -> {
                projectionList.add(Projections.property(projectionsdto.getPropertyName()),projectionsdto.getPropertyName());
            });

            Criteria criteria =  session.createCriteria(entityClass);
            criteria.setProjection(projectionList);

            restrictionsList.forEach( (restrictionsdto) -> {
                RestrictionsConditions condition = restrictionsdto.getConditions();
                switch (condition) {

                    case EQ :
                        Criterion eqCriterion = Restrictions.eq(restrictionsdto.getPropertyName(), restrictionsdto.getPropertyValue());
                        criteria.add(eqCriterion);
                        break;

                    case GT :
                        Criterion gtCriterion = Restrictions.gt(restrictionsdto.getPropertyName(), restrictionsdto.getPropertyValue());
                        criteria.add(gtCriterion);
                        break;

                    case LT :
                        Criterion ltCriterion = Restrictions.gt(restrictionsdto.getPropertyName(), restrictionsdto.getPropertyValue());
                        criteria.add(ltCriterion);
                        break;

                    case ILIKE :
                        Criterion ilikeCriterion = Restrictions.ilike(restrictionsdto.getPropertyName(), restrictionsdto.getPropertyValue());
                        criteria.add(ilikeCriterion);
                        break;

                    default : break;
                }
            });
            criteria.setResultTransformer(Transformers.aliasToBean(entityClass));
            return (List<T>) criteria.list();

        }catch(Exception e) {
            logger.error("(:) Error in loadEntitiesByCriteria Method two columns (:)");
            e.printStackTrace();
        }
        finally {
            closeSession(session);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> loadEntitiesByCriteria(Class<T> entityClass,String propertyName, Serializable serializableValue) {

        logger.info("loadEntitiesByCriteria() : Entity Class ->> "+entityClass+" PropertyName ->> "+propertyName+" Serializable Value ->>"+serializableValue);

        Session session = null;
        try {
            session = getSession();
            return (List<T>) session.createCriteria(entityClass)
                    .add(Restrictions.eq(propertyName, serializableValue))
                    .list();
        }catch(Exception e) {
            logger.error("(:) Error in loadEntitiesByCriteria Method (:)");
            e.printStackTrace();
        }
        finally {
            closeSession(session);
        }
        return null;
    }
    @SuppressWarnings("unchecked")
    public <T> List<T> loadEntitiesByHQL(String hqlQuery) {

        logger.info("loadEntitiesByHQL() : HQL Query ->> "+hqlQuery);

        Session session = null;
        try {
            session = getSession();
            return (List<T>) session.createQuery(hqlQuery).list();
        }catch(Exception e) {
            logger.error("(:) Error in LoadEntitiesByHQL Method (:)");
            e.printStackTrace();
        }
        finally {
            closeSession(session);
        }
        return null;
    }

    public <T> Serializable findValueByPropertyName(Class<T> entityClass,String propertyName,Serializable propertyValue,String propertyNameValueToReturn) {

        logger.info("findValueByPropertyName() : Entity Class ->> "+entityClass+" <<- Property Name ->>"+propertyName+" <<- Property Value ->> "+propertyValue+" <<- PropertyNameValue To Return ->> "+propertyNameValueToReturn);

        Session session = null;
        try {
            session = getSession();
            Object returnedObject =  session.createCriteria(entityClass)
                    .setProjection(Projections.property(propertyNameValueToReturn))
                    .add(Restrictions.eq(propertyName, propertyValue))
                    .uniqueResult();

            logger.info("Serializable Single Value ->> "+returnedObject);

            return (Serializable)returnedObject;

        }catch(Exception e) {
            logger.error("(:) Error in findValueByPropertyName Method (:)");
            e.printStackTrace();
        }
        finally {
            closeSession(session);
        }
        return null;
    }

    public String updateEntityByHQL(String hqlQuery) {

        logger.info("updateEntityByCriteria() : HQL Query ->> "+hqlQuery);

        Session session = null;
        try {
            session = getSession();
            Query query = session.createQuery(hqlQuery);
            query.executeUpdate();
            //return ReturnTypes.SUCCESS;
            return "SUCCESS"; //TODO : Need to uncomment above line
        }catch(Exception e) {
            logger.error("(:) Error in UpdateEntityByHQL Method (:)");
            e.printStackTrace();
        }
        finally {
            closeSession(session);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> loadEntitiesByCriteria(Class<T> entityClass, ArrayList<String> projectionPropertyList, String propertyName, Serializable serializableValue) {

        logger.info("loadEntitiesByCriteria() : Entity Class ->> "+entityClass+" PropertyName ->> "+propertyName+" Serializable Value ->>"+serializableValue+" ProjectionPropertyList Size ->> "+projectionPropertyList.size());

        Session session = null;
        try {
            session = getSession();
            ProjectionList projectionList = Projections.projectionList();
            for(String projectionPropertyName : projectionPropertyList) {
                projectionList.add(Projections.property(projectionPropertyName),projectionPropertyName);
            }
            return (List<T>) session.createCriteria(entityClass)
                    .setProjection(projectionList)
                    .add(Restrictions.eq(propertyName, serializableValue))
                    .setResultTransformer(Transformers.aliasToBean(entityClass))
                    .list();
        }catch(Exception e) {
            logger.error("(:) Error in loadEntitiesByCriteria Method (:)");
            e.printStackTrace();
        }
        finally {
            closeSession(session);
        }
        return null;
    }


    @SuppressWarnings("unchecked")
    public <T> List<Map<String,Object>> loadEntitiesByCriteriaAsAliasToMap(Class<T> entityClass, ArrayList<String> projectionPropertyList, String propertyName, Serializable serializableValue) {

        logger.info("loadEntitiesByCriteriaAsAliasToMap() : Entity Class ->> "+entityClass+" PropertyName ->> "+propertyName+" Serializable Value ->>"+serializableValue+" ProjectionPropertyList Size ->> "+projectionPropertyList.size());

        Session session = null;
        try {
            session = getSession();
            ProjectionList projectionList = Projections.projectionList();
            for(String projectionPropertyName : projectionPropertyList) {
                projectionList.add(Projections.property(projectionPropertyName),projectionPropertyName);
            }
            return (List<Map<String,Object>>) session.createCriteria(entityClass)
                    .setProjection(projectionList)
                    .add(Restrictions.eq(propertyName, serializableValue))
                    .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
                    .list();
        }catch(Exception e) {
            logger.error("(:) Error in loadEntitiesByCriteria Method (:)");
            e.printStackTrace();
        }
        finally {
            closeSession(session);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> loadDetailsBySqlQuery(String sqlQuery) {
        Session session = null;
        try {
            session = getSession();
            SQLQuery query = session.createSQLQuery(sqlQuery);
            return (List<Object[]>) query.list();
        } catch (Exception e) {
            logger.error("(:) Error in GetEntityByCriteria Method two columns (:)");
            e.printStackTrace();
        } finally {
            closeSession(session);
        }
        return null;
    }

//    @SuppressWarnings("unchecked")
    public Integer uploadDataBySqlQuery(String sqlQuery) {
        Session session = null;
        try {
            session = getSession();
            SQLQuery query = session.createSQLQuery(sqlQuery);
            return query.executeUpdate();
        } catch (Exception e) {
            logger.error("(:) Error in GetEntityByCriteria Method two columns (:)");
            e.printStackTrace();
        } finally {
            closeSession(session);
        }
        return null;
    }

//    @SuppressWarnings("unchecked")
    public <T>Object getEntitiesCountByCriteria(Class<T> entityClass) {

        logger.info("getEntitiesCountByCriteria() : Entity Class ->> "+entityClass);

        Session session = null;
        try {
            session = getSession();
            return session.createCriteria(entityClass).setProjection(Projections.rowCount()).uniqueResult();
        }catch(Exception e) {
            logger.error("(:) Error in getEntitiesCountByCriteria Method (:)");
            e.printStackTrace();
        }
        finally {
            closeSession(session);
        }
        return null;
    }


}

*/
