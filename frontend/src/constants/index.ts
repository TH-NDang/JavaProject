import { Home, Users, FileText, BarChart, Star } from 'lucide-react';
import { MenuItem } from '../types';
import { userRoles, rolePermissions } from '../utils/roles';

export const allMenuItems: MenuItem[] = [
    { icon: Home, label: 'Trang chủ' },
    { icon: Users, label: 'Khách hàng' },
    { icon: FileText, label: 'Đơn hàng' },
    { icon: BarChart, label: 'Thi công' },
    { icon: FileText, label: 'Báo cáo' },
    { icon: Star, label: 'Đánh giá' },
];
