import React from 'react';
import { FileText, Users, Star } from 'lucide-react';
import StatCard from './StatCard';

const HomePage: React.FC = () => (
  <div className="mt-4">
    <div className="flex flex-wrap -mx-6">
      <StatCard icon={FileText} title="Đơn hàng mới" value="8,282" />
      <StatCard icon={Users} title="Khách hàng" value="200,521" />
      <StatCard icon={Star} title="Đánh giá" value="215,542" />
    </div>
  </div>
);

export default HomePage;
