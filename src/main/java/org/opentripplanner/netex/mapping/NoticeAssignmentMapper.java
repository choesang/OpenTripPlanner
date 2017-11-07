package org.opentripplanner.netex.mapping;

import org.opentripplanner.model.NoticeAssignment;
import org.opentripplanner.graph_builder.model.NetexDao;
import org.rutebanken.netex.model.JourneyPattern;
import org.rutebanken.netex.model.ServiceJourney;

import java.util.ArrayList;
import java.util.Collection;

public class NoticeAssignmentMapper {

    public Collection<NoticeAssignment> mapNoticeAssignment(org.rutebanken.netex.model.NoticeAssignment netexNoticeAssignment, NetexDao netexDao){
        Collection<NoticeAssignment> noticeAssignments = new ArrayList<>();

        if (getObjectType(netexNoticeAssignment).equals("StopPointInJourneyPattern")) {
            JourneyPattern journeyPattern = netexDao.getJourneyPatternByStopPointId().get(netexNoticeAssignment.getNoticedObjectRef().getRef());

            // Map notice from StopPointInJourneyPattern to corresponding TimeTabledPassingTimes
            for (ServiceJourney serviceJourney : netexDao.getServiceJourneyById().get(journeyPattern.getId())) {
                org.opentripplanner.model.NoticeAssignment otpNoticeAssignment = new org.opentripplanner.model.NoticeAssignment();

                otpNoticeAssignment.setId(AgencyAndIdFactory.getAgencyAndId(netexNoticeAssignment.getId()));
                otpNoticeAssignment.setNoticeId(AgencyAndIdFactory.getAgencyAndId(netexNoticeAssignment.getNoticeRef().getRef()));
                otpNoticeAssignment.setElementId(AgencyAndIdFactory.getAgencyAndId(serviceJourney.getId()));

                noticeAssignments.add(otpNoticeAssignment);
            }
        } else {
            org.opentripplanner.model.NoticeAssignment otpNoticeAssignment = new org.opentripplanner.model.NoticeAssignment();

            otpNoticeAssignment.setId(AgencyAndIdFactory.getAgencyAndId(netexNoticeAssignment.getId()));
            otpNoticeAssignment.setNoticeId(AgencyAndIdFactory.getAgencyAndId(netexNoticeAssignment.getNoticeRef().getRef()));
            otpNoticeAssignment.setElementId(AgencyAndIdFactory.getAgencyAndId(netexNoticeAssignment.getNoticedObjectRef().getRef()));

            noticeAssignments.add(otpNoticeAssignment);
        }

        return noticeAssignments;
    }

    private String getObjectType (org.rutebanken.netex.model.NoticeAssignment netexNoticeAssignment) {
        String objectType = "";
        if (netexNoticeAssignment.getNoticedObjectRef() != null
                && netexNoticeAssignment.getNoticedObjectRef().getRef().split(":").length >= 2) {
            objectType = netexNoticeAssignment.getNoticedObjectRef().getRef().split(":")[1];
        }
        return objectType;
    }
}