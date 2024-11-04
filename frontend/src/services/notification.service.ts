export interface EmailNotification {
  to: string;
  subject: string;
  content: string;
}

export class NotificationService {
  // Giả định hàm gửi email
  static async sendEmail(notification: EmailNotification): Promise<void> {
    // Trong thực tế, đây sẽ là API call đến backend để gửi email
    console.log("Sending email:", notification);
    return new Promise((resolve) => {
      setTimeout(resolve, 1000);
    });
  }

  static async sendContactStatusUpdate(
    email: string,
    name: string,
    status: keyof typeof statusMessages
  ): Promise<void> {
    const statusMessages = {
      PROCESSING: "đang được xử lý",
      COMPLETED: "đã được xử lý",
      REJECTED: "đã bị từ chối",
    };

    const subject = "Cập nhật trạng thái yêu cầu liên hệ";
    const content = `
      Kính gửi ${name},
      
      Yêu cầu liên hệ của bạn ${statusMessages[status] || "đã được cập nhật"}.
      
      Trân trọng,
      Koi Pond Co.
    `;

    await this.sendEmail({
      to: email,
      subject,
      content,
    });
  }
}
