package com.emr.custom;

import java.util.Set;

import org.mule.DefaultMuleEvent;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.routing.AggregationContext;
import org.mule.routing.AggregationStrategy;

public class CustomAggregationStrategy implements AggregationStrategy {

	@Override
	public MuleEvent aggregate(AggregationContext context) throws MuleException {
		StringBuilder responseBuilder = new StringBuilder();
		MuleEvent result = DefaultMuleEvent.copy(context.collectEventsWithoutExceptions().get(0));

		try {
			for (MuleEvent event : context.collectEventsWithoutExceptions()) {
				// Get Attachments and set attachments to the result message
				Set<String> attachmentNames = event.getMessage().getOutboundAttachmentNames();
				for (String s : attachmentNames) {
					result.getMessage().addOutboundAttachment(s, event.getMessage().getOutboundAttachment(s));
				}

				responseBuilder.append(event.getMessage().getPayloadAsString() + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error in response");
		}

		result.getMessage().setPayload(responseBuilder.toString());

		return result;
	}

}
