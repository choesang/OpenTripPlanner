package org.opentripplanner.netex.mapping;

import org.opentripplanner.model.NoticeAssignment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoticeAssignmentMapper {
    public NoticeAssignment mapNoticeAssignment(org.rutebanken.netex.model.NoticeAssignment netexNoticeAssignment){
        NoticeAssignment otpNoticeAssignment = new NoticeAssignment();

        otpNoticeAssignment.setId(AgencyAndIdFactory.getAgencyAndId(netexNoticeAssignment.getId()));
        otpNoticeAssignment.setNoticeId(AgencyAndIdFactory.getAgencyAndId(netexNoticeAssignment.getNoticeRef().getRef()));
        otpNoticeAssignment.setElementId(AgencyAndIdFactory.getAgencyAndId(netexNoticeAssignment.getNoticedObjectRef().getRef()));

        return otpNoticeAssignment;
    }
}