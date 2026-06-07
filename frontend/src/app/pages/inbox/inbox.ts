import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ChatService } from '../../services/chat';

@Component({
  selector: 'app-inbox',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './inbox.html',
  styleUrl: './inbox.css'
})
export class InboxComponent implements OnInit {

  messages: any[] = [];
  message = '';

  constructor(private chatService: ChatService) {}

  ngOnInit() {
    this.chatService.getInbox().subscribe({
      next: (data) => this.messages = data,
      error: () => this.message = 'Please login to view inbox.'
    });
  }
}