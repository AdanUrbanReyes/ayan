class LinkedList{

	constructor(value, next){
		this.value = value;
		this.next = next;
	}

}

function printLinkedList(head){	
	i = head;
	s = "";
	while(i){
		s += (" -> " + i.value);
		i = i.next;
	}
	console.log(s.substring(4));
}

function reverseLinkedList(head){
	b = head;
	m = b.next;
	b.next = undefined;
	while(m){
		e = m.next;
		m.next = b;
		b = m;
		m = e;
	}
	return b;
}

head = new LinkedList("a", undefined);
head.next = new LinkedList("b", undefined);
head.next.next = new LinkedList("c", undefined);
head.next.next.next = new LinkedList("d", undefined);

printLinkedList(head);
head = reverseLinkedList(head);
printLinkedList(head);
