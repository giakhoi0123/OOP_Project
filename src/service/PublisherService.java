package service;

import model.Publisher;

public class PublisherService {
    private Publisher[] publishers = new Publisher[50];
    private static int publisherCount = 0;
    public void addPublisher() {
        Publisher publisher = new Publisher();
        publishers[publisherCount++] = publisher;
        publisher.nhap();

    }
    public Publisher getPublisherById(String publisherId) {
        for (int i = 0; i < publisherCount; i++) {
            if (publishers[i].getPublisherId().equals(publisherId)) {
                return publishers[i];
            }
        }
        return null;
    }
}
