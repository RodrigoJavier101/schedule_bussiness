package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.conexionMongoDB;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.Block;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;

public class ConexionMongo {

    private static MongoClient mClient;

    // Singleton for MongoClient
    // Creates a single connection pool internally
    private MongoClient getMongoClient() {
        if (mClient == null) {
            mClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        }
        return mClient;
    }

    // Utility method to get database instance
    private MongoDatabase getDB() {
        return getMongoClient().getDatabase("test");
    }

    // Utility method to get user collection
    private MongoCollection<Document> getUserCollection() {
        return getDB().getCollection("user");
    }

    public static void main(String[] args) {
        ConexionMongo demo = new ConexionMongo();
        demo.insertUser();
        demo.queryUsers();
    }

    // Read all documents from user collection
    private void queryUsers() {
        getUserCollection().find().forEach(new Block<Document>() {
            @Override
            public void apply(Document t) {
                System.out.println(t);
            }
        });
    }

    // Insert a document in user collection
    private void insertUser() {
        Document document = new Document("username","qpt")
                            .append("email", "testemail@example.com");
        getUserCollection().insertOne(document);
    }
}