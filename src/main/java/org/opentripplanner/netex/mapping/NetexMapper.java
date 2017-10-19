package org.opentripplanner.netex.mapping;

import org.opentripplanner.graph_builder.model.NetexDao;
import org.opentripplanner.model.Route;
import org.opentripplanner.model.Stop;
import org.opentripplanner.model.Transfer;
import org.opentripplanner.model.impl.OtpTransitDaoBuilder;
import org.rutebanken.netex.model.JourneyPattern;
import org.rutebanken.netex.model.Line;
import org.rutebanken.netex.model.Notice;
import org.rutebanken.netex.model.Operator;
import org.rutebanken.netex.model.StopPlace;

import java.util.Collection;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class NetexMapper {

    final OtpTransitDaoBuilder transitBuilder;

    AgencyMapper agencyMapper = new AgencyMapper();

    RouteMapper routeMapper = new RouteMapper();

    StopMapper stopMapper = new StopMapper();

    TripPatternMapper tripPatternMapper = new TripPatternMapper();

    CalendarMapper calendarMapper = new CalendarMapper();

    NoticeMapper noticeMapper = new NoticeMapper();

    NoticeAssignmentMapper noticeAssignmentMapper = new NoticeAssignmentMapper();

    TransferMapper transferMapper = new TransferMapper();

    public NetexMapper(OtpTransitDaoBuilder transitBuilder) {
        this.transitBuilder = transitBuilder;
    }

    public OtpTransitDaoBuilder mapNetexToOtp(NetexDao netexDao) {
        for (Operator operator : netexDao.getOperators().values()) {
            if (operator != null) {
                transitBuilder.getAgencies().add(agencyMapper.mapAgency(operator, "Europe/Oslo"));
            }
        }

        for (Line line : netexDao.getLineById().values()) {
            if (line != null) {
                Route route = routeMapper.mapRoute(line, transitBuilder);
                transitBuilder.getRoutes().add(route);
            }
        }

        for (StopPlace stopPlace : netexDao.getStopPlaceMap().values()) {
            if (stopPlace != null) {
                Collection<Stop> stops = stopMapper.mapParentAndChildStops(stopPlace, netexDao.getParentStopPlaceById());
                for (Stop stop : stops) {
                    transitBuilder.getStops().add(stop);
                }
            }
        }

        for (JourneyPattern journeyPattern : netexDao.getJourneyPatternsById().values()) {
            if (journeyPattern != null) {
                tripPatternMapper.mapTripPattern(journeyPattern, transitBuilder, netexDao);
            }
        }

        for (String serviceId : netexDao.getServiceIds().values()) {
            transitBuilder.getCalendarDates().addAll(calendarMapper
                    .mapToCalendarDates(AgencyAndIdFactory.getAgencyAndId(serviceId), netexDao));
        }

        for (Notice notice : netexDao.getNoticeMap().values()) {
            if (notice != null) {
                org.opentripplanner.model.Notice otpNotice = noticeMapper.mapNotice(notice);
                transitBuilder.getNoticesById().add(otpNotice);
            }
        }

        for (org.rutebanken.netex.model.NoticeAssignment noticeAssignment : netexDao
                .getNoticeAssignmentMap().values()) {
            if (noticeAssignment != null) {
                org.opentripplanner.model.NoticeAssignment otpNoticeAssignment = noticeAssignmentMapper
                        .mapNoticeAssignment(noticeAssignment);
                transitBuilder.getNoticeAssignmentsById().add(otpNoticeAssignment);
            }
        }

        for (org.rutebanken.netex.model.ServiceJourneyInterchange interchange : netexDao.getInterchanges().values()) {
            if (interchange != null) {
                Transfer transfer = transferMapper.mapTransfer(interchange, transitBuilder, netexDao);
                if (transfer != null) {
                    transitBuilder.getTransfers().add(transfer);
                }
            }
        }
        return transitBuilder;
    }
}
