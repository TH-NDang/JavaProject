import React from 'react';
import HomePage from './HomePage';
import CustomerManagement from './CustomerManagement';
import OrderManagement from './OrderManagement';
import ConstructionManagement from './ConstructionManagement';
import ReportManagement from './ReportManagement';
import ReviewManagement from './ReviewManagement';
import { userRoles } from '../../utils/roles';

interface ContentAreaProps {
    activeMenuItem: string;
    userRole: keyof typeof userRoles;
}

const ContentArea: React.FC<ContentAreaProps> = ({ activeMenuItem, userRole }) => {
    switch (activeMenuItem) {
        case 'Trang chủ':
            return <HomePage userRole={userRole} />;
        case 'Khách hàng':
            return <CustomerManagement />;
        case 'Đơn hàng':
            return <OrderManagement />;
        case 'Thi công':
            return <ConstructionManagement />;
        case 'Báo cáo':
            return <ReportManagement />;
        case 'Đánh giá':
            return <ReviewManagement />;
        default:
            return <div className="bg-white p-6 rounded-lg shadow-sm">Chọn một mục từ menu</div>;
    }
};

export default ContentArea;
