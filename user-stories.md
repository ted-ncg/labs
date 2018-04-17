Effective teams with third party dependencies, or those that rely on external specialist knowledge, generally dedicate one or two people to investigate those dependencies a week or two before starting a story. The results of the investigations are a great starting point for the story analysis discussions. Some teams analyse stories twice as a group: once a week or two ahead of the iteration to collect open questions and focus the upfront investigation, and the second time just ahead of the iteration to communicate and agree on the details. 

avoid feature requests. If you have only a short summary on a card, it must not be a solution without context. So, ‘How much potential cash is in blocked projects?’ is a valid summary, but a ‘Cash report’ isn’t.

Independent and valuable are often difficult to reconcile with small. The value of software is a vague and esoteric concept in the domain of business users, but task size is under the control of a delivery team, so many teams end up choosing size over value. 

Describing expected changes also allows teams to assess whether a story succeeds from a business perspective once it is delivered. 

In many cases we also need an ‘instead of’ clause to follow the ‘what’. 

By approaching stories as survivable experiments we can shift the focus from technical complexity to expected outcomes and learning. This prevents technical stories, stories that are too small to go live and stories that business sponsors don’t care about. 

If you have a big chunk of work, identify underlying assumptions and think about the easiest way of proving or disproving them. Design experiments around those assumptions and turn them into user stories. 

Avoid ‘as a user’ statements like the plague. Avoid overly generic customer segment descriptions of any kind. 

A good guideline to think about is that the user need of a story (‘In order to…’) should ideally be in the sphere of influence of the delivery team, and the deliverable (‘I want…’) should ideally be in their zone of control. 

By doing the zone of control/sphere of influence triage on stories, we quickly identified tasks that are at risk of being delayed, so the team started on them quickly. Such stories are also worth splitting into a part that is actionable by the delivery group, and a part that needs management intervention or coordination. 

Flexible scope is great when you need to react quickly, but frequent fire-fighting is unsustainable. When a team is reacting to an emergency, other work suffers and quick and dirty changes cause a lot of technical debt. In most cases that we’ve seen, someone knew about the upcoming deadline months in advance, but this was not clearly communicated. 

Write down the best before date on those stories 

Turning on a siren and rushing through only works if most of the cars on the road are not ambulances as well. 

it might be a good idea to create a separate lane on your board for fixed-date items, 

propose time-constrained stories a certain length of time before the deadline, much in the way that airplane companies require passengers to check in at least 30 minutes before their flight. 

In Why the FBI Can’t Build a Case Management System , Jerome Israel highlights one of the biggest risks of iterative delivery...due to pressure to show progress, development turned towards easy tasks and serious risks always got postponed. Small, easy wins always get prioritised over difficult tasks, like children choosing between candy and broccoli.

periodically shifting gears during delivery and alternating between ‘paying to learn’ (addressing major risks) and ‘paying to get business value’. 

Creating a hierarchical backlogs can also prevent turning delivery into a stream of consciousness and never dealing with big risks. A sure way to lose the asteroids game is to break up all the big rocks early. 

I’ve facilitated several workshops where stakeholders decided to simplify their business processes instead of complicating internal IT systems after realising that most of their ‘must have’ items are actually just parity features. 

Teams rarely plan for handling people that do not get value or provide a service, but have a interest in the initiative or a hidden agenda that might interfere with the plan. Alistair Cockburn calls those stakeholders ‘off-stage actors’ in Writing Effective Use Cases , and I love that name because it immediately brings the mental image of someone behind the curtain. 

group the post-its according to how important your product or initiative is to a stakeholder (interest) and how much they can make their preference about a topic happen (power). 

start by asking the question, ‘How will we demonstrate this story?’ 

Instead of slicing the future system by inputs, slice it by outputs, and then build up the capability to produce these outputs incrementally. 

a system that serves valuable data in production is not as easy to kill politically as a system that after six months of build-up has great registration forms but still does not provide useful outputs.

Don’t give everyone 2% of what they need, instead give 2% of users everything they need. 

those monstrosities sometimes known as technical stories. 

Get the team to decide on the output of a learning story before starting. 

incredibly valuable when there is a tight business deadline, carrying a significant risk, such as an upcoming regulatory change or expiry of a contract. In situations like that, extracting basic utility can help to relieve the pressure. Most of the larger story can be done later, if the basic utility part can be shipped on time. 
- For each of the steps, list options that would support different levels of quality. For example, manually sending emails supports very low volumes and infrequent sending. 
- It’s important to explore unsatisfactory levels of quality because this might generate some good ideas, and because the group might discover that it’s OK to skip a step. 

tasks were masked into fake user stories, written in the typical story format. ‘As a QA in order to test faster I want automated log error reports’ is not a user story. It might be in a story format, but unless you are building software for testers (these people were not), there is no good reason why you’d want to capture tasks like that. 
Writing such tasks as user stories makes them compete with externally valuable work, and they’ll always lose. 
Decide upfront. Set a budget, both an allowance and a limit, and just let the team get on with that. Discount for that budget in short term capacity planning for customer-centric work. 
Stakeholders will always choose externally valuable items over internally valuable tasks, 
The budget also serves as a limit on the impact of internal tasks to customer-centric work, and ensures that real user stories don’t suffer. 

Story sizing is useful for one purpose: deciding whether a story is too big to implement or small enough to get fast feedback. -story sizes aren’t really useful for anything else. 
The average time it takes for a story to go through the pipeline is a far better measurement for long-term planning than any kind of estimate of story size. 

a single story rarely delivers everything needed to complete a business objective. Because of this, plans typically include lots of small stories. 

If all key decision-makers agree that fraud reduction is currently more important than faster payments, this decision is easier to uphold when someone comes with a seemingly great product idea that would actually just disrupt delivery. When the plan is just ten randomly selected stories, it is almost impossible to justify rejecting an arbitrary change. 
When the priority is set on the level of business impacts, unjustified pet features easily get postponed. 
Never say ‘no’ - say ‘not now’ 

Provide a low-friction channel for stakeholders to change direction at regular intervals
Provide a high-friction channel for someone to push through a critical change between those intervals 

For example, if a test in the feature sets area fails, the team can sound the alarm and break the current build. On the other hand, if a test in the current iteration area fails, that’s expected - the team is still building that feature. 