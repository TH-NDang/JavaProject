import React from 'react';
import { LucideIcon } from 'lucide-react';

interface StatCardProps {
  icon: LucideIcon;
  title: string;
  value: string;
}

const StatCard: React.FC<StatCardProps> = ({ icon: Icon, title, value }) => (
    <div className="w-full px-6 sm:w-1/2 xl:w-1/3 mt-6 sm:mt-0">
      <div className="flex items-center px-5 py-6 shadow-sm rounded-md bg-white border">
        <div className="p-3 rounded-full bg-blue-100 bg-opacity-75">
          <Icon className="h-8 w-8 text-blue-600" />
        </div>
        <div className="mx-5">
          <h4 className="text-2xl font-semibold text-gray-700">{value}</h4>
          <div className="text-gray-500">{title}</div>
        </div>
      </div>
    </div>
);

export default StatCard;
