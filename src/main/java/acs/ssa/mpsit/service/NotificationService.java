package acs.ssa.mpsit.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acs.ssa.mpsit.dto.Notification;
import acs.ssa.mpsit.model.NotificationEntity;
import acs.ssa.mpsit.repository.NotificationRepository;

@Service
public class NotificationService {

	@Autowired
	private NotificationRepository notificationRepository;

	@Autowired
	private Mapper mapper;

	public List<Notification> getAllNotifications() {

		List<NotificationEntity> notificationEntities = notificationRepository.findAll();
		List<Notification> notifications = new ArrayList<Notification>();

		for(NotificationEntity notificationEntity : notificationEntities) {
			notifications.add(mapper.map(notificationEntity, Notification.class));
		}

		return notifications;
	}

	public void addNotification(String message) {
		NotificationEntity notificationEntity = new NotificationEntity();
		notificationEntity.setMessage(message);
		notificationEntity.setDate(new Date());

		notificationRepository.save(notificationEntity);
	}
}
